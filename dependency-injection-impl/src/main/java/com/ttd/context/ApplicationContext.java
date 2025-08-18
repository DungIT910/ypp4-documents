package com.ttd.context;

import com.ttd.annotation.Qualifier;

import java.beans.Introspector;
import java.util.Set;

public class ApplicationContext {
    private final BeanFactory beanFactory = new BeanFactory();

    public ApplicationContext(String basePackage) {
        scan(basePackage);
    }

    private void scan(String basePackage) {
        Set<Class<?>> componentClasses = ClassPathScanner.findComponentClasses(basePackage);
        for (Class<?> clazz : componentClasses) {
            String name = Introspector.decapitalize(clazz.getSimpleName());
            if (clazz.isAnnotationPresent(Qualifier.class)) {
                String qualifier = clazz.getAnnotation(Qualifier.class).value();
                beanFactory.registerBeanDefinition(name, qualifier, new BeanDefinition(clazz));
            } else {
                beanFactory.registerBeanDefinition(name, new BeanDefinition(clazz));
            }
        }
    }

    public <T> T getBean(Class<T> type) {
        return beanFactory.getBean(type);
    }
}
