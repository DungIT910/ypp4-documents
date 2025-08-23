package com.ttd.beancontainer;

import com.ttd.beandefinition.BeanDefinition;
import com.ttd.beandefinition.model.BeanScope;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class BeanContainer {
    private final Map<String, BeanDefinition> beans = new HashMap<>();

    public static String classToBeanName(Class<?> clazz) {
        String simpleName = clazz.getSimpleName();
        return Character.toLowerCase(simpleName.charAt(0)) + simpleName.substring(1);
    }

    public boolean registerBean(String beanName, Class<?> clazz) {
        return Optional.ofNullable(clazz)
                .map(c -> {
                    String key = Optional.ofNullable(beanName).orElseGet(() -> classToBeanName(c));
                    BeanScope scope = Optional.ofNullable(c.getAnnotation(Scope.class))
                            .map(Scope::value)
                            .orElse(BeanScope.SINGLETON);

                    BeanDefinition beanDefinition = new BeanDefinition();
                    beanDefinition.setBeanName(key);
                    beanDefinition.setType(c);
                    beanDefinition.setScope(scope);
                    beanDefinition.setInstance(null);

                    beans.put(key, beanDefinition);
                    return true;
                })
                .orElse(false);
    }

    private Object resolveBeanInstance(BeanDefinition definition) {
        if (definition.getScope() == BeanScope.PROTOTYPE) {
            return createInstance(definition.getType());
        }

        return Optional.ofNullable(definition.getInstance())
                .orElseGet(() -> {
                    Object newInstance = createInstance(definition.getType());
                    definition.setInstance(newInstance);
                    return newInstance;
                });
    }

    public Object getBeanByName(String beanName) {
        return Optional.ofNullable(beans.get(beanName))
                .map(this::resolveBeanInstance)
                .orElse(null);
    }

    public Object getBeanByType(Class<?> type) {
        return getBeanByName(classToBeanName(type));
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