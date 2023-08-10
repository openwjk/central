package com.openwjk.central.web.aop;

import com.alibaba.fastjson2.JSON;
import com.openwjk.central.commons.utils.ThreadLocalUtil;
import com.openwjk.central.web.utils.IpUtil;
import com.openwjk.commons.domain.ResponseVO;
import com.openwjk.commons.enums.ResponseEnum;
import com.openwjk.commons.utils.Constant;
import com.openwjk.commons.utils.DateUtil;
import com.openwjk.commons.utils.RandomCodeUtil;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/9 14:03
 */
@Aspect
@Component
@Order(0)
@Log4j2
public class ApiCommonAspect {
    @Around("(execution(* com.openwjk.central.web.controller.*.*(..)))")
    public Object aroundApi(ProceedingJoinPoint pjp) throws Throwable {
        long beginTs = DateUtil.getCurrentTimeMillis();
        String logId = generateLogId();
        setThreadLocal(logId);
        Method targetMethod = getTargetMethod(pjp);

        Object[] args = pjp.getArgs();
        HttpServletRequest request = getRequestObj();
        String ip = IpUtil.getIp(request);
        String uri = "";

        Class<?> targetClazz = getTargetClazz(pjp);
        uri = getOrBuildUri(request, targetClazz, targetMethod);
        doApiLogBegin(uri, args);
        doApiLogIp(uri, ip);

        Object retObj;
        try {
            retObj = pjp.proceed(args);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            retObj = JSON.toJSONString(
                    new ResponseVO(ResponseEnum.SYSTEM_ERROR.getCode(), ResponseEnum.SYSTEM_ERROR.getMsg()));
        }

        doApiLogEnd(uri, beginTs);

        removeThreadLocal();
        return retObj;
    }

    private void doApiLogBegin(String uri, Object[] args) {
        log.info("call uri: {} begin, params: {}", uri, JSON.toJSONString(args));
    }

    private void doApiLogEnd(String uri, long beginTs) {
        long endTs = DateUtil.getCurrentTimeMillis();
        log.info("call uri: {} end, takes {}ms", uri, endTs - beginTs);
    }

    private void doApiLogIp(String uri, String ip) {
        log.info("call uri: {}, ip: {}", uri, ip);
    }

    private void removeThreadLocal() {
        ThreadLocalUtil.removeLogId();
    }

    private String getOrBuildUri(HttpServletRequest request, Class<?> targetClazz, Method targetMethod) {
        if (null != request) {
            return request.getRequestURI();
        } else {
            return buildUri(targetClazz, targetMethod);
        }
    }

    private String buildUri(Class<?> targetClazz, Method targetMethod) {
        try {
            RequestMapping clazzAnno = targetClazz.getAnnotation(RequestMapping.class);
            String uri = getUriFromRequestMapping(clazzAnno);

            RequestMapping methodAnno = targetMethod.getAnnotation(RequestMapping.class);
            uri += getUriFromRequestMapping(methodAnno);
            return uri;
        } catch (Exception e) {
            log.info(e.getMessage());
            return "";
        }
    }

    private String getUriFromRequestMapping(RequestMapping requestMapping) {
        if (requestMapping.value().length > 0) {
            return requestMapping.value()[0];
        } else {
            return "";
        }
    }

    private Class<?> getTargetClazz(ProceedingJoinPoint pjp) {
        return pjp.getTarget().getClass();
    }

    private HttpServletRequest getRequestObj() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        } catch (Exception e) {
            log.warn("call ApiCommonAspect.getRequestObj, occur exception, maybe the request from dubbo.");
            return null;
        }
    }

    private Method getTargetMethod(ProceedingJoinPoint pjp) throws NoSuchMethodException {
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        return pjp.getTarget().getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
    }

    private void setThreadLocal(String logId) {
        ThreadLocalUtil.setLogId(logId);
    }

    private String generateLogId() {
        try {
            return RandomCodeUtil.generateRandomNo("CENTRAL", Constant.INT_TEN);
        } catch (Exception e) {
            log.error(String.format("generate ReqLogId error, e.getMessage:%s", e.getMessage()), e);
            return null;
        }
    }
}
