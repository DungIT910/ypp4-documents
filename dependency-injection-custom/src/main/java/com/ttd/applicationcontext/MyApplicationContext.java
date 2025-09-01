package com.ttd.applicationcontext;

import com.ttd.annotation.MyComponent;
import com.ttd.beancontainer.BeanContainer;
import com.ttd.dependencyinjector.DependencyInjector;
import com.ttd.scanner.ClassScanner;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

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
        final Annotation[] annotations = clazz.getAnnotations();
        String beanName = null;

        for (Annotation annotation : annotations) {
            Class<? extends Annotation> annotationType = annotation.annotationType();
            if (annotationType.equals(MyComponent.class) || annotationType.isAnnotationPresent(MyComponent.class)) {
                try {
                    Method valueMethod = annotationType.getMethod("value");
                    String annotationValue = (String) valueMethod.invoke(annotation);

                    beanName = Optional.ofNullable(annotationValue)
                            .map(String::trim)
                            .filter(Predicate.not(String::isBlank))
                            .orElseGet(() -> BeanContainer.convertClassToBeanName(clazz));
                } catch (Exception e) {
                    beanName = null;
                }
            }
        }
        return beanName;
    }

    public Object getBeanByName(String beanName) {
        return beanContainer.getBeanByName(beanName);
    }
}
