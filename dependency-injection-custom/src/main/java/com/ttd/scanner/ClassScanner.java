package com.ttd.scanner;

import com.ttd.annotation.Component;

import java.io.File;
import java.net.URL;
import java.util.*;

public class ClassScanner {
    private ClassScanner() {
        // private constructor
    }

    private static boolean isTestClassPath(URL resource) {
        return resource.getPath().contains("/test-classes/");
    }

    public static Set<Class<?>> findClassesWithComponent(String basePackage) throws Exception {
        String path = basePackage.replace('.', '/');
        Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(path);

        Set<Class<?>> classSet = new HashSet<>();
        Collections.list(resources).stream()
                .filter(resource -> !isTestClassPath(resource))
                .map(resource -> {
                    try {
                        return new File(resource.toURI());
                    } catch (Exception e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .filter(File::exists)
                .forEach(baseDir -> collectComponentClasses(baseDir, basePackage, classSet));

        return classSet;
    }


    private static void collectComponentClasses(File currentDir, String packageName, Set<Class<?>> classSet) {
        Arrays.stream(Optional.ofNullable(currentDir.listFiles()).orElse(new File[0]))
                .forEach(file -> {
                    Runnable action = chooseFileAction(file, packageName, classSet);
                    action.run();
                });
    }

    private static Runnable chooseFileAction(File file, String packageName, Set<Class<?>> classSet) {
        if (file.isDirectory()) {
            String subPackage = packageName + "." + file.getName();
            return () -> collectComponentClasses(file, subPackage, classSet);
        }

        if (file.getName().endsWith(".class")) {
            String className = packageName + "." + file.getName().replace(".class", "");
            return () -> {
                try {
                    tryLoadComponent(className, classSet);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            };
        }

        return () -> {
        };
    }

    private static void tryLoadComponent(String className, Set<Class<?>> classSet) throws ClassNotFoundException {
        Class<?> clazz = Class.forName(className);
        if (clazz.isAnnotationPresent(Component.class)) {
            classSet.add(clazz);
        }
    }
}
