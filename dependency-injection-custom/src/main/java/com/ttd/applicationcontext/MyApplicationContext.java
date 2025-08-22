package com.ttd.applicationcontext;

import com.ttd.annotation.MyComponent;
import com.ttd.beancontainer.BeanContainer;
import com.ttd.scanner.ClassScanner;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class MyApplicationContext {
    private final BeanContainer beanContainer;

    public MyApplicationContext(BeanContainer beanContainer) {
        this.beanContainer = beanContainer;
    }

    public void initialize(String basePackage) throws Exception {
        Set<Class<?>> componentClasses = ClassScanner.findClassesWithComponent(basePackage);
        for (Class<?> clazz : componentClasses) {
            String beanName = getBeanNameFromAnnotation(clazz);
            beanContainer.registerBean(beanName, clazz);
        }
    }

    private String getBeanNameFromAnnotation(Class<?> clazz) {
        Optional<String> annotationValue = Arrays.stream(clazz.getAnnotations())
                .map(Annotation::annotationType)
                .filter(annoType -> annoType == MyComponent.class || annoType.isAnnotationPresent(MyComponent.class))
                .map(annoType -> {
                    try {
                        Method valueMethod = annoType.getMethod("value");
                        Annotation annotation = clazz.getAnnotation(annoType);
                        return (String) valueMethod.invoke(annotation);
                    } catch (Exception e) {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(value -> !value.isBlank())
                .findFirst();

        return annotationValue.orElseGet(() -> BeanContainer.classToBeanName(clazz));
    }


    public Object getBeanByName(String beanName) {
        return beanContainer.getBeanByName(beanName);
    }
}
