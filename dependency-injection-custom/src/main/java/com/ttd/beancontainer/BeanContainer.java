package com.ttd.beancontainer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class BeanContainer {
    private final Map<Class<?>, Object> beans = new HashMap<>();

    public void registerBean(Class<?> clazz) throws Exception {
        Object instance = clazz.getDeclaredConstructor().newInstance();
        beans.put(clazz, instance);
    }

    public Object getBean(Class<?> clazz) {
        return beans.get(clazz);
    }

    public Collection<Object> getAllBeans() {
        return beans.values();
    }
}
