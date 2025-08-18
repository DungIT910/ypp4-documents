package com.ttd.context;

import com.ttd.annotation.Autowired;
import com.ttd.annotation.PostConstruct;
import com.ttd.annotation.Qualifier;

import java.beans.Introspector;
import java.lang.reflect.*;
import java.util.*;

public class BeanFactory {
    private final Map<String, Object> singletonBeans = new HashMap<>();
    private final Map<String, BeanDefinition> beanDefinitions = new HashMap<>();
    private final Set<String> creatingBeans = new HashSet<>();

    public void registerBeanDefinition(String name, BeanDefinition def) {
        String beanName = Introspector.decapitalize(name);
        beanDefinitions.put(beanName, def);
    }

    public void registerBeanDefinition(String name, String qualifier, BeanDefinition def) {
        String beanName = (qualifier != null && !qualifier.isEmpty()) ? qualifier : Introspector.decapitalize(name);
        beanDefinitions.put(beanName, def);
    }

    public Object getBean(String name) {
        String beanName = Introspector.decapitalize(name);
        if (singletonBeans.containsKey(beanName)) return singletonBeans.get(beanName);

        if (creatingBeans.contains(beanName)) {
            throw new RuntimeException("Circular dependency detected while creating bean: " + beanName);
        }

        BeanDefinition def = beanDefinitions.get(beanName);
        if (def == null) throw new RuntimeException("No bean named " + beanName);

        creatingBeans.add(beanName);
        Object bean = createBean(def);
        creatingBeans.remove(beanName);

        singletonBeans.put(beanName, bean);
        return bean;
    }

    public <T> T getBean(Class<T> type) {
        return type.cast(getBean(type.getSimpleName()));
    }

    private Object createBean(BeanDefinition def) {
        try {
            Class<?> clazz = def.getBeanClass();
            Object instance = clazz.getDeclaredConstructor().newInstance();

            // Field injection
            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(Autowired.class)) {
                    field.setAccessible(true);
                    String beanName;

                    if (field.isAnnotationPresent(Qualifier.class)) {
                        beanName = field.getAnnotation(Qualifier.class).value();
                    } else {
                        beanName = Introspector.decapitalize(field.getType().getSimpleName());
                    }

                    Object dependency = getBean(beanName);
                    field.set(instance, dependency);
                }
            }

            // @PostConstruct
            for (Method method : clazz.getDeclaredMethods()) {
                if (method.isAnnotationPresent(PostConstruct.class)) {
                    method.setAccessible(true);
                    method.invoke(instance);
                }
            }

            return instance;
        } catch (Exception e) {
            throw new RuntimeException("Failed to create bean for class: " + def.getBeanClass().getName(), e);
        }
    }
}
