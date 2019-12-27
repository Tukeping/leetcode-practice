package com.tukeping.tools.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author tukeping
 * @date 2019/12/25
 **/
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.METHOD})
public @interface Cost {
}
