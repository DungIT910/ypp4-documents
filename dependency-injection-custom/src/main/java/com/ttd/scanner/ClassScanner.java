package com.ttd.scanner;

import com.ttd.annotation.MyComponent;

import java.io.File;
import java.lang.annotation.Annotation;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Stream;

public class ClassScanner {
    private ClassScanner() {
    }

    public static Set<Class<?>> findClassesWithComponent(String basePackage) throws Exception {
        String path = basePackage.replace('.', '/');
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Set<Class<?>> classes = new HashSet<>();

        Collections.list(classLoader.getResources(path))
                .stream()
                .filter(url -> !url.getPath().contains("/test-classes/"))
                .map(url -> {
                    try {
                        return new File(url.toURI());
                    } catch (URISyntaxException e) {
                        throw new RuntimeException(e);
                    }
                })
                .filter(File::exists)
                .forEach(dir -> scanDirectory(dir, basePackage, classes));

        return classes;
    }

    private static void scanDirectory(File dir, String packageName, Set<Class<?>> classes) {
        Stream.of(Objects.requireNonNull(dir.listFiles()))
                .filter(Objects::nonNull)
                .forEach(file -> processFile(file, packageName, classes));
    }

    private static void processFile(File file, String packageName, Set<Class<?>> classes) {
        Stream.of(file)
                .filter(File::exists)
                .forEach(f -> {
                    String newPackage = packageName + "." + f.getName();
                    if (f.isDirectory()) {
                        scanDirectory(f, newPackage, classes);
                    } else if (f.getName().endsWith(".class")) {
                        Stream.of(newPackage.replace(".class", ""))
                                .map(ClassScanner::loadClass)
                                .filter(Optional::isPresent)
                                .map(Optional::get)
                                .filter(clazz -> !clazz.isAnnotation())
                                .filter(ClassScanner::hasMyComponentAnnotation)
                                .forEach(classes::add);
                    }
                });
    }

    private static Optional<Class<?>> loadClass(String className) {
        try {
            return Optional.of(Class.forName(className));
        } catch (ClassNotFoundException e) {
            return Optional.empty();
        }
    }

    private static boolean hasMyComponentAnnotation(Class<?> clazz) {
        return clazz.isAnnotationPresent(MyComponent.class) ||
               Stream.of(clazz.getAnnotations())
                       .map(Annotation::annotationType)
                       .anyMatch(a -> a.isAnnotationPresent(MyComponent.class));
    }
}