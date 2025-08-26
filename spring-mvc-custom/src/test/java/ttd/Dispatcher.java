package ttd;

import com.sun.net.httpserver.HttpExchange;
import com.ttd.annotation.MyRequestMapping;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Dispatcher {

    private final Map<RouteKey, HandlerMethod> handlerMap = new HashMap<>();

    public Dispatcher() {
        scanControllers(); // gán vào handlerMap
    }

    private void scanControllers() {
        // Hardcoded tạm thời để test pass, sau này dùng classpath scan
        HelloController controller = new HelloController();
        for (Method method : HelloController.class.getDeclaredMethods()) {
            if (method.isAnnotationPresent(MyRequestMapping.class)) {
                MyRequestMapping mapping = method.getAnnotation(MyRequestMapping.class);
                RouteKey key = new RouteKey(mapping.path(), mapping.method());
                handlerMap.put(key, new HandlerMethod(controller, method));
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
                String html = ViewResolver.render(mv);
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

