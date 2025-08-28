package com.ttd.framework.web.dispatcher;

import com.sun.net.httpserver.HttpExchange;
import com.ttd.framework.annotation.MyController;
import com.ttd.framework.annotation.MyRequestMapping;
import com.ttd.framework.annotation.MyPathVariable;
import com.ttd.framework.annotation.RequestMethod;
import com.ttd.framework.container.BeanContainer;
import com.ttd.framework.web.view.ModelAndView;
import com.ttd.framework.web.view.ViewResolver;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MyDispatcher {

    private final List<RoutePattern> routePatterns = new ArrayList<>();
    private final BeanContainer beanContainer;
    private final ViewResolver viewResolver;

    public MyDispatcher(BeanContainer beanContainer) {
        this.beanContainer = beanContainer;
        this.viewResolver = new ViewResolver();
    }

    public void init() {
        beanContainer.getAllBeans().stream()
                .filter(bean -> bean.getClass().isAnnotationPresent(MyController.class))
                .forEach(this::processControllerBean);
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

    private void processMethod(Object bean, Method method) throws Exception {
        if (method.isAnnotationPresent(MyRequestMapping.class)) {
            MyRequestMapping mapping = method.getAnnotation(MyRequestMapping.class);
            RoutePattern pattern = createRoutePattern(mapping.path(), mapping.method(), new HandlerMethod(bean, method));
            routePatterns.add(pattern);
        }

        Arrays.stream(method.getDeclaredAnnotations())
                .filter(a -> !a.annotationType().equals(MyRequestMapping.class))
                .filter(a -> a.annotationType().isAnnotationPresent(MyRequestMapping.class))
                .forEach(a -> {
                    try {
                        Method pathMethod = a.annotationType().getMethod("path");
                        String path = (String) pathMethod.invoke(a);
                        MyRequestMapping metaMapping = a.annotationType().getAnnotation(MyRequestMapping.class);
                        RoutePattern pattern = createRoutePattern(path, metaMapping.method(), new HandlerMethod(bean, method));
                        routePatterns.add(pattern);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    private static RoutePattern createRoutePattern(String path, RequestMethod method, HandlerMethod handler) {
        List<String> variableNames = new ArrayList<>();
        String regex = Arrays.stream(path.split("/"))
                .filter(s -> !s.isEmpty())
                .map(segment -> {
                    if (segment.startsWith("{") && segment.endsWith("}")) {
                        String varName = segment.substring(1, segment.length() - 1);
                        variableNames.add(varName);
                        return "([^/]+)";
                    } else {
                        return Pattern.quote(segment);
                    }
                })
                .collect(Collectors.joining("/", "/", ""));
        regex = "^" + regex + "$";
        return new RoutePattern(regex, method, handler, variableNames);
    }

    public void handle(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        RequestMethod rqMethod = RequestMethod.valueOf(exchange.getRequestMethod());

        HandlerMethod handler = null;
        Map<String, String> pathVars = new HashMap<>();

        for (RoutePattern rp : routePatterns) {
            if (rp.method() == rqMethod) {
                Matcher matcher = Pattern.compile(rp.pattern()).matcher(path);
                if (matcher.matches()) {
                    handler = rp.handler();
                    for (int i = 0; i < rp.variableNames().size(); i++) {
                        pathVars.put(rp.variableNames().get(i), matcher.group(i + 1));
                    }
                    break;
                }
            }
        }

        if (handler == null) {
            String notFound = "404 Not Found";
            exchange.sendResponseHeaders(404, notFound.length());
            exchange.getResponseBody().write(notFound.getBytes());
            exchange.getResponseBody().close();
            return;
        }

        try {
            Method method = handler.method;
            Object controller = handler.controller;

            Object[] args = Arrays.stream(method.getParameters())
                    .map(param -> {
                        if (param.isAnnotationPresent(MyPathVariable.class)) {
                            String name = param.getAnnotation(MyPathVariable.class).value();
                            return pathVars.get(name);
                        } else {
                            return null;
                        }
                    }).toArray();

            Object result = method.invoke(controller, args);

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

    // --- Supporting classes ---
    private record RoutePattern(String pattern, RequestMethod method, HandlerMethod handler, List<String> variableNames) {}

    private static class HandlerMethod {
        Object controller;
        Method method;

        HandlerMethod(Object controller, Method method) {
            this.controller = controller;
            this.method = method;
        }
    }
}
