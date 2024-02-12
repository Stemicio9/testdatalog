package com.example.testdatalogclient.utilities.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Traceable {
    boolean cpuUsage () default true;
    boolean memoryUsage() default true;
}
