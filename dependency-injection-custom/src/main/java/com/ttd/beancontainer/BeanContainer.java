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

    public void registerBean(String beanName, Class<?> clazz) throws Exception {
        Object instance = clazz.getDeclaredConstructor().newInstance();
        String key = Optional.ofNullable(beanName)
                .orElseGet(() -> classToBeanName(clazz));

        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanName(key);
        beanDefinition.setType(clazz);
        beanDefinition.setInstance(instance);
        beanDefinition.setScope(BeanScope.SINGLETON);

        beans.put(key, beanDefinition);
    }

    public Object getBeanByName(String beanName) {
        BeanDefinition definition = beans.get(beanName);
        return (definition != null) ? definition.getInstance() : null;
    }

    public Object getBeanByType(Class<?> type) {
        String beanName = classToBeanName(type);
        return getBeanByName(beanName);
    }

    public Collection<Object> getAllBeans() {
        return beans.values().stream()
                .map(BeanDefinition::getInstance)
                .collect(Collectors.toList());
    }
}