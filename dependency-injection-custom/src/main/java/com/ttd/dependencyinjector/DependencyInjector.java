package com.ttd.dependencyinjector;

import com.ttd.annotation.MyAutowired;
import com.ttd.annotation.MyQualifier;
import com.ttd.beancontainer.BeanContainer;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class DependencyInjector {
    private final BeanContainer container;

    public DependencyInjector(BeanContainer container) {
        this.container = container;
    }

    public boolean injectDependencies(Object target) {
        return Arrays.stream(target.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(MyAutowired.class))
                .map(field -> {
                    field.setAccessible(true);
                    return injectField(field, target);
                })
                .reduce(true, (result, injected) -> result && injected);
    }

    private boolean injectField(Field field, Object target) {
        try {
            return Optional.ofNullable(field.getAnnotation(MyQualifier.class))
                    .map(qualifier -> {
                        String beanName = qualifier.value();
                        Object dependency = container.getBeanByName(beanName);
                        return dependency != null && field.getType().isAssignableFrom(dependency.getClass())
                               && setField(field, target, dependency);
                    })
                    .orElseGet(() -> {
                        Collection<Object> allBeans = container.getAllBeans();
                        List<Object> matchingBeans = allBeans.stream()
                                .filter(bean -> field.getType().isAssignableFrom(bean.getClass()))
                                .toList();
                        return matchingBeans.size() == 1 && setField(field, target, matchingBeans.get(0));
                    });
        } catch (Exception e) {
            return false;
        }
    }

    private boolean setField(Field field, Object target, Object dependency) {
        try {
            field.set(target, dependency);
            return true;
        } catch (IllegalAccessException e) {
            return false;
        }
    }
}