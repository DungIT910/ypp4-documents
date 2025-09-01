package com.ttd.scanner;

import com.ttd.annotation.MyComponent;
import com.ttd.scanner.exception.MyClassScannerException;
import com.ttd.scanner.exception.MyIOException;
import com.ttd.scanner.exception.MyURISyntaxException;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

public final class ClassScanner {
    private ClassScanner() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static Set<Class<?>> findClassesWithComponent(String basePackage) throws MyClassScannerException {
        String path = basePackage.replace('.', '/');
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Set<Class<?>> classes = new HashSet<>();

        try {
            Collection<URL> resources = Collections.list(classLoader.getResources(path));

            for (URL url : resources) {
                if (url.getPath().contains("/test-classes/")) {
                    continue;
                }

                File directory = new File(url.toURI());
                if (directory.exists()) {
                    scanDirectory(directory, basePackage, classes);
                }
            }
        } catch (IOException e) {
            throw new MyIOException(e);
        } catch (URISyntaxException e) {
            throw new MyURISyntaxException(e);
        }

        return classes;
    }

    private static void scanDirectory(File dir, String packageName, Set<Class<?>> classes) {
        File[] files = dir.listFiles();

        if (files == null) {
            return;
        }

        for (File file : files) {
            processFile(file, packageName, classes);
        }
    }

    private static void processFile(File file, String packageName, Set<Class<?>> classes) {
        String newPackage = packageName + "." + file.getName();

        if (file.isDirectory()) {
            scanDirectory(file, newPackage, classes);
        }

        if (!file.getName().endsWith(".class")) {
            return;
        }

        String packageWithoutClassExtension = newPackage.replace(".class", "");
        Optional<Class<?>> loadedClass = loadClass(packageWithoutClassExtension);

        loadedClass.filter(Predicate.not((Class<?> c) -> c.isAnnotation())
                        .and(ClassScanner::hasMyComponentAnnotation))
                .ifPresent(classes::add);
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
