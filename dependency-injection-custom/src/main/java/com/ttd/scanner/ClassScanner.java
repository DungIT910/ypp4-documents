package com.ttd.scanner;

import java.util.HashSet;
import java.util.Set;

public class ClassScanner {
    public static Set<Class<?>> findClassesWithComponent(String basePackage) {
        Set<Class<?>> componentClasses = new HashSet<>();
        return Set.of();
    }
    public String packageToPath(String packageName) {
        return packageName.replace('.', '/');
    }
}