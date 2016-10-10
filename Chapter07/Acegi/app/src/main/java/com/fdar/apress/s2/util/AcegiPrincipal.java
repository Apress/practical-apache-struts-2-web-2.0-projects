package com.fdar.apress.s2.util;

import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AcegiPrincipal {
}
