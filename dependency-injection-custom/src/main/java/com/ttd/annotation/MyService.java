package com.ttd.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@MyComponent
public @interface MyService {
    String value() default "";
}
