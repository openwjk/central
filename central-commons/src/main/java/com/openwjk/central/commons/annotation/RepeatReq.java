package com.openwjk.central.commons.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/10 14:35
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RepeatReq {
    String value() default "";

    long expire() default 30L;

    /**
     * 使用spel表达式
     * @return
     */
    String [] key() default "";
}
