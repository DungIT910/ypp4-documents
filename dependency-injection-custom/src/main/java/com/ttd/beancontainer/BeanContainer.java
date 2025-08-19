package com.ttd.beancontainer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class BeanContainer {
    // Map key(class type) to value(object - instance of that class)
    private final Map<Class<?>, Object> beans = new HashMap<>();

    // Create new object from class and register/store it in the container
    public void registerBean(Class<?> clazz) throws Exception {
        Object instance = clazz.getDeclaredConstructor().newInstance();
        beans.put(clazz, instance);
    }

    // Get the object from the container by class type
    public Object getBean(Class<?> clazz) {
        return beans.get(clazz);
    }

    // Get all objects stored in the container
    public Collection<Object> getAllBeans() {
        return beans.values();
    }
}
