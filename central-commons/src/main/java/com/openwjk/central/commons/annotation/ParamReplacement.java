package com.openwjk.central.commons.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wangjunkai
 * @description
 * @date 2023/12/21 15:41
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ParamReplacement {
    String replacement() default "";
    String regex() default "";
}
