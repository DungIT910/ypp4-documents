package com.ttd.framework.web.dispatcher;

import com.sun.net.httpserver.HttpExchange;
import com.ttd.framework.annotation.MyController;
import com.ttd.framework.annotation.MyRequestMapping;
import com.ttd.framework.annotation.RequestMethod;
import com.ttd.framework.container.BeanContainer;
import com.ttd.framework.web.view.ModelAndView;
import com.ttd.framework.web.view.ViewResolver;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.*;

public class MyDispatcher {

    private final Map<RouteKey, HandlerMethod> handlerMap = new HashMap<>();
    private final BeanContainer beanContainer;
    private final ViewResolver viewResolver;

    public MyDispatcher(BeanContainer beanContainer) {
        this.beanContainer = beanContainer;
        this.viewResolver = new ViewResolver();
    }

    public void init() {
        beanContainer.getAllBeans().stream()
                .filter(bean -> bean.getClass().isAnnotationPresent(MyController.class))
                .forEach(bean -> {
                    try {
                        processControllerBean(bean);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    private void processControllerBean(Object bean) {
        Class<?> clazz = bean.getClass();

        Arrays.stream(clazz.getDeclaredMethods())
                .forEach(method -> {
                    try {
                        processMethod(bean, method);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    private void processMethod(Object bean, Method method) {
        Optional.of(method)
                .filter(m -> m.isAnnotationPresent(MyRequestMapping.class))
                .ifPresent(m -> {
                    MyRequestMapping mapping = m.getAnnotation(MyRequestMapping.class);
                    RouteKey key = new RouteKey(mapping.path(), mapping.method());
                    handlerMap.put(key, new HandlerMethod(bean, method));
                });

        Arrays.stream(method.getDeclaredAnnotations())
                .filter(annotation -> !annotation.annotationType().equals(MyRequestMapping.class))
                .filter(annotation -> annotation.annotationType().isAnnotationPresent(MyRequestMapping.class))
                .forEach(annotation -> {
                    try {
                        Method pathMethod = annotation.annotationType().getMethod("path");
                        String path = (String) pathMethod.invoke(annotation);
                        MyRequestMapping metaMapping = annotation.annotationType().getAnnotation(MyRequestMapping.class);
                        RouteKey key = new RouteKey(path, metaMapping.method());
                        handlerMap.put(key, new HandlerMethod(bean, method));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    public void handle(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        String method = exchange.getRequestMethod();

        RequestMethod rqMethod = RequestMethod.valueOf(method);
        HandlerMethod handler = handlerMap.get(new RouteKey(path, rqMethod));
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

    private record RouteKey(String path, RequestMethod method) {
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof RouteKey route)) return false;
            return path.equals(route.path) && method == route.method;
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

