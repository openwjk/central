package com.openwjk.central.web.aop;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;
import com.openwjk.central.commons.annotation.RateLimit;
import com.openwjk.central.commons.utils.RedisLockUtil;
import com.openwjk.central.service.helper.SpelHelper;
import com.openwjk.commons.enums.ResponseEnum;
import com.openwjk.commons.exception.RateLimitException;
import com.openwjk.commons.utils.Constant;
import com.openwjk.commons.utils.ParamCheckUtil;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * @author wangjunkai
 * @description
 * @date 2023/8/10 14:33
 */
@Aspect
@Component
@Order(10)
@Log4j2
public class RateLimiterAspect extends AbstractAop {
    private static final Map<String, RateLimiter> rateMap = Maps.newConcurrentMap();

    @Autowired
    SpelHelper spelHelper;
    @Autowired
    RedisLockUtil redisLockUtil;

    @Around("execution(* com.openwjk.central.web.controller.*.*(..))")
    public Object aroundApi(ProceedingJoinPoint pjp) throws Throwable {
        String key = getMethodAllPath(pjp);
        Method method = getTargetMethod(pjp);
        RateLimit anni = method.getAnnotation(RateLimit.class);
        if (anni != null && ParamCheckUtil.checkIsNumber(anni.value())) {
            rateLimit(rateMap, key, anni);
        } else {
            rateLimit(rateMap, Constant.DEFAULT, anni);
        }
        return pjp.proceed();
    }

    private synchronized void putRateLimiter(String key, RateLimit anni) {
        if (!rateMap.containsKey(key)) {
            RateLimiter rateLimiter = RateLimiter.create(Double.parseDouble(anni == null ? Constant.STRING_THIRTY : anni.value()));
            rateMap.put(key, rateLimiter);
        }
    }

    private void rateLimit(Map<String, RateLimiter> rateMap, String key, RateLimit anni) {
        if (rateMap.containsKey(key)) {
            checkRateLimit(rateMap, key);
        } else {
            putRateLimiter(key, anni);
            checkRateLimit(rateMap, key);
        }
    }

    private void checkRateLimit(Map<String, RateLimiter> rateMap, String key) {
        if (!rateMap.get(key).tryAcquire(1, TimeUnit.SECONDS)) {
            throw new RateLimitException(ResponseEnum.NETWORK_BUSY.getMsg());
        }
    }


}
