package com.openwjk.central.commons.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wangjunkai
 * @description
 * @date 2023/12/13 16:28
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApplyCheckParam {
    String[] complex() default "";

    /**
     * support spel.
     * verify the parameters of the basic type.
     */
    String briefness() default "";

    String strategy() default "";
}
