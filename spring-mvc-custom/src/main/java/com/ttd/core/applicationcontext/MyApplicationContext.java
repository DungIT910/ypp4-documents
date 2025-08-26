package com.ttd.core.applicationcontext;

import com.ttd.annotation.MyComponent;
import com.ttd.core.beancontainer.BeanContainer;
import com.ttd.core.dependencyinjector.DependencyInjector;
import com.ttd.core.scanner.ClassScanner;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class MyApplicationContext {
    private final BeanContainer beanContainer;
    private final DependencyInjector dependencyInjector;

    public MyApplicationContext(BeanContainer beanContainer, DependencyInjector dependencyInjector) {
        this.beanContainer = beanContainer;
        this.dependencyInjector = dependencyInjector;
    }

    public void initialize(String basePackage) throws Exception {
        Set<Class<?>> componentClasses = ClassScanner.findClassesWithComponent(basePackage);
        for (Class<?> clazz : componentClasses) {
            String beanName = getBeanNameFromAnnotation(clazz);
            beanContainer.registerBean(beanName, clazz);
        }
        injectDependencies();
    }

    public void injectDependencies() {
        beanContainer.getAllBeans().forEach(dependencyInjector::injectDependencies);
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
