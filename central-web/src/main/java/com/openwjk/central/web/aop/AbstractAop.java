package com.openwjk.central.web.aop;

import com.openwjk.commons.utils.Constant;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/11 10:20
 */
public abstract class AbstractAop {
    protected String getMethodAllPath(ProceedingJoinPoint pjp) throws NoSuchMethodException {
        return getTargetClazz(pjp).getName() + Constant.POUND_KEY + getTargetMethod(pjp).getName();
    }

    protected Method getTargetMethod(ProceedingJoinPoint pjp) throws NoSuchMethodException {
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        return pjp.getTarget().getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
    }

    protected Class<?> getTargetClazz(ProceedingJoinPoint pjp) {
        return pjp.getTarget().getClass();
    }
}
