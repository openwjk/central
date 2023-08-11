package com.openwjk.central.web.aop;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;
import com.openwjk.central.commons.annotation.RateLimit;
import com.openwjk.central.commons.enums.ScheduledTaskEnum;
import com.openwjk.central.commons.utils.RedisLockUtil;
import com.openwjk.central.service.helper.SpelHelper;
import com.openwjk.central.service.service.ScheduledService;
import com.openwjk.commons.enums.ResponseEnum;
import com.openwjk.commons.exception.RateLimitException;
import com.openwjk.commons.utils.Constant;
import com.openwjk.commons.utils.ParamCheckUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


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
    private static final Map<String, RateLimiter> rateMap = Maps.newHashMapWithExpectedSize(1);
    static {
        RateLimiter rateLimiter = RateLimiter.create(Double.valueOf(Constant.STRING_THIRTY));
        rateMap.put(Constant.DEFAULT, rateLimiter);
    }

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
            if (rateMap.containsKey(key)) {
                rateLimit(rateMap, key);
                pjp.proceed();
            } else {
                RateLimiter rateLimiter = RateLimiter.create(Double.valueOf(anni.value()));
                rateMap.put(key, rateLimiter);
                rateLimit(rateMap, key);
                pjp.proceed();
            }
        } else {
            rateLimit(rateMap, Constant.DEFAULT);
        }
        return pjp.proceed();
    }

    private void rateLimit(Map<String, RateLimiter> rateMap, String key) {
        if (!rateMap.get(key).tryAcquire()) {
            throw new RateLimitException(ResponseEnum.NETWORK_BUSY.getMsg());
        }
    }


}
