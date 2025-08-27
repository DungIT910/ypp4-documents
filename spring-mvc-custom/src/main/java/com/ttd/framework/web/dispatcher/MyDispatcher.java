package com.ttd.framework.web.dispatcher;

import com.sun.net.httpserver.HttpExchange;
import com.ttd.framework.annotation.MyController;
import com.ttd.framework.annotation.MyRequestMapping;
import com.ttd.framework.container.BeanContainer;
import com.ttd.framework.web.view.ModelAndView;
import com.ttd.framework.web.view.ViewResolver;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MyDispatcher {

    private final Map<RouteKey, HandlerMethod> handlerMap = new HashMap<>();
    private final BeanContainer beanContainer;
    private final ViewResolver viewResolver;

    public MyDispatcher(BeanContainer beanContainer) {
        this.beanContainer = beanContainer;
        this.viewResolver = new ViewResolver();
    }

    public void init() {
        scanControllers();
    }

    private void scanControllers() {
        for (Object bean : beanContainer.getAllBeans()) {
            Class<?> clazz = bean.getClass();

            if (!clazz.isAnnotationPresent(MyController.class)) continue;

            for (Method method : clazz.getDeclaredMethods()) {
                if (method.isAnnotationPresent(MyRequestMapping.class)) {
                    MyRequestMapping mapping = method.getAnnotation(MyRequestMapping.class);
                    RouteKey key = new RouteKey(mapping.path(), mapping.method());
                    handlerMap.put(key, new HandlerMethod(bean, method));
                }
            }
        }
    }

    public void handle(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        String method = exchange.getRequestMethod();

        HandlerMethod handler = handlerMap.get(new RouteKey(path, method));
        if (handler == null) {
            String notFound = "404 Not Found";
            exchange.sendResponseHeaders(404, notFound.length());
            exchange.getResponseBody().write(notFound.getBytes());
            exchange.getResponseBody().close();
            return;
        }

        try {
            Object result = handler.method.invoke(handler.controller);
            if (result instanceof ModelAndView mv) {
                String html = viewResolver.render(mv);
                byte[] bytes = html.getBytes();
                exchange.sendResponseHeaders(200, bytes.length);
                OutputStream os = exchange.getResponseBody();
                os.write(bytes);
                os.close();
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to invoke handler", e);
        }
    }

    private record RouteKey(String path, String method) {
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof RouteKey route)) return false;
            return path.equals(route.path) && method.equals(route.method);
        }

        @Override
        public int hashCode() {
            return Objects.hash(path, method);
        }
    }

    private static class HandlerMethod {
        Object controller;
        Method method;

        HandlerMethod(Object controller, Method method) {
            this.controller = controller;
            this.method = method;
        }
    }
}

