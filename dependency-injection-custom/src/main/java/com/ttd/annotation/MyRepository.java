package com.ttd.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@MyComponent
public @interface MyRepository {
    String value() default "";
}