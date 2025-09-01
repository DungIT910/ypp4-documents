package com.ttd.beancontainer;

import com.ttd.annotation.Scope;
import com.ttd.beandefinition.BeanDefinition;
import com.ttd.beandefinition.model.BeanScope;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class BeanContainer {
    private final Map<String, BeanDefinition> beans = new HashMap<>();

    public static String convertClassToBeanName(Class<?> clazz) {
        String simpleName = clazz.getSimpleName();
        return Character.toLowerCase(simpleName.charAt(0)) + simpleName.substring(1);
    }

    public void registerBean(String beanName, Class<?> clazz) {
        String key = Optional.ofNullable(beanName).orElseGet(() -> convertClassToBeanName(clazz));
        BeanScope scope = Optional.ofNullable(clazz.getAnnotation(Scope.class))
                .map(Scope::value)
                .orElse(BeanScope.SINGLETON);

        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanName(key);
        beanDefinition.setType(clazz);
        beanDefinition.setScope(scope);
        beanDefinition.setInstance(null);

        beans.put(key, beanDefinition);
    }

    private Object resolveBeanInstance(BeanDefinition definition) {
        Class<?> definitionType = definition.getType();
        if (BeanScope.PROTOTYPE.equals(definition.getScope())) {
            return createInstance(definitionType);
        }

        Object instance = definition.getInstance();
        if (instance != null) {
            return instance;
        }

        Object newInstance = createInstance(definitionType);
        definition.setInstance(newInstance);

        return newInstance;
    }

    public Object getBeanByName(String beanName) {
        BeanDefinition beanDefinition = beans.get(beanName);

        if (beanDefinition == null) {
            return null;
        }

        return resolveBeanInstance(beanDefinition);
    }

    public Collection<Object> getAllBeans() {
        return beans.values().stream()
                .map(this::resolveBeanInstance)
                .filter(Objects::nonNull)
                .toList();
    }

    private Object createInstance(Class<?> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            return null;
        }
    }
}
