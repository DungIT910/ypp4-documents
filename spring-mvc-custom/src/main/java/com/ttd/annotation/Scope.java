package com.ttd.annotation;

import com.ttd.core.beandefinition.model.BeanScope;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Scope {
    BeanScope value() default BeanScope.SINGLETON;
}
