package com.ttd.dependencyinjector;

import com.ttd.beancontainer.BeanContainer;

import java.lang.reflect.Field;

public class DependencyInjector {
    private final BeanContainer container;

    public DependencyInjector(BeanContainer container) {
        this.container = container;
    }

    public void injectDependencies(Object target) throws IllegalAccessException {
        for (Field field : target.getClass().getDeclaredFields()) {
        }
    }
}