package com.openwjk.central.commons.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wangjunkai
 * @description
 * @date 2023/12/21 15:36
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ParamCondition {
    /**
     * support spel. example: (#param == null || #param.length() == 0)
     * used for determine if the parameters are valid.
     */
    String condition() default "";

    boolean notEmpty() default false;

    boolean isComplex() default false;

    String verifyHandler() default "";
}
