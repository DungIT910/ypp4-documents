package com.ttd.framework.annotation;

import com.ttd.framework.context.BeanScope;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyScope {
    BeanScope value() default BeanScope.SINGLETON;
}
