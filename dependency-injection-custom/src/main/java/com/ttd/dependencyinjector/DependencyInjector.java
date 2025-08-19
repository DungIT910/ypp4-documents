package com.ttd.dependencyinjector;

import com.ttd.annotation.Autowired;
import com.ttd.beancontainer.BeanContainer;

import java.lang.reflect.Field;

public class DependencyInjector {
    private final BeanContainer container;

    public DependencyInjector(BeanContainer container) {
        this.container = container;
    }

    public void injectDependencies() throws IllegalAccessException {
        for (Object bean : container.getAllBeans()) {
            Class<?> clazz = bean.getClass();
            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(Autowired.class)) {
                    Object dependency = container.getBean(field.getType());
                    field.setAccessible(true);
                    field.set(bean, dependency);
                }
            }
        }
    }

}
