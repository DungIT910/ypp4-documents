package com.ttd.framework.web.view;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;

public class ViewResolver {

    private static final String VIEW_PREFIX = "/views/";
    private static final String VIEW_SUFFIX = ".html";

    private static String loadTemplate(String viewName) {
        String path = VIEW_PREFIX + viewName + VIEW_SUFFIX;

        InputStream inputStream = ViewResolver.class.getResourceAsStream(path);
        if (inputStream == null) {
            throw new RuntimeException("View template not found: " + path);
        }

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            return reader.lines().collect(Collectors.joining("\n"));

        } catch (Exception e) {
            throw new RuntimeException("Failed to load view: " + path, e);
        }
    }

    private static String replacePlaceholders(String template, Map<String, Object> model) {
        for (Map.Entry<String, Object> entry : model.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            String placeholder = "${" + key + "}";
            template = template.replace(placeholder, String.valueOf(value));

            if (value != null) {
                Class<?> clazz = value.getClass();
                for (Field field : clazz.getDeclaredFields()) {
                    field.setAccessible(true);
                    try {
                        Object fieldValue = field.get(value);
                        String nestedPlaceholder = "${" + key + "." + field.getName() + "}";
                        template = template.replace(nestedPlaceholder, String.valueOf(fieldValue));
                    } catch (IllegalAccessException ignored) {
                    }
                }
            }
        }

        return template;
    }

    public String render(ModelAndView mv) {
        String viewName = mv.getViewName();
        Map<String, Object> model = mv.getModel();

        String template = loadTemplate(viewName);
        template = replacePlaceholders(template, model);

        return template;
    }
}
