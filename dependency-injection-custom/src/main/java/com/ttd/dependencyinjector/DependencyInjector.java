package com.ttd.dependencyinjector;

import com.ttd.annotation.MyAutowired;
import com.ttd.annotation.MyQualifier;
import com.ttd.beancontainer.BeanContainer;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;

public class DependencyInjector {
    private final BeanContainer container;

    public DependencyInjector(BeanContainer container) {
        this.container = container;
    }

    public void injectDependencies(Object target) {
        Field[] fields = target.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (!field.isAnnotationPresent(MyAutowired.class)) {
                continue;
            }

            field.setAccessible(true);
            injectField(field, target);
        }
    }

    private void injectField(Field field, Object target) {
        MyQualifier qualifier = field.getAnnotation(MyQualifier.class);

        if (qualifier == null) {
            injectFieldWithoutQualifier(field, target);
            return;
        }

        injectFieldWithQualifier(field, target, qualifier);
    }

    private void injectFieldWithoutQualifier(Field field, Object target) {
        Collection<Object> allBeans = container.getAllBeans();
        List<Object> matchingBeans = allBeans.stream()
                .filter(bean -> field.getType().isAssignableFrom(bean.getClass()))
                .toList();

        if (matchingBeans.size() != 1) {
            return;
        }

        setField(field, target, matchingBeans.get(0));
    }

    private void injectFieldWithQualifier(Field field, Object target, MyQualifier qualifier) {
        String beanName = qualifier.value();
        Object dependency = container.getBeanByName(beanName);

        if (!field.getType().isAssignableFrom(dependency.getClass())) {
            return;
        }

        setField(field, target, dependency);
    }

    private void setField(Field field, Object target, Object dependency) {
        try {
            field.set(target, dependency);
        } catch (IllegalAccessException ignored) {
        }
    }
}
