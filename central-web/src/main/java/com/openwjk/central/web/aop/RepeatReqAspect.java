package com.openwjk.central.web.aop;

import com.alibaba.fastjson2.JSON;
import com.openwjk.central.commons.annotation.RepeatReq;
import com.openwjk.central.commons.utils.RedisLockUtil;
import com.openwjk.central.commons.utils.RedisUtil;
import com.openwjk.central.service.helper.SpelHelper;
import com.openwjk.central.web.utils.IpUtil;
import com.openwjk.commons.enums.ResponseEnum;
import com.openwjk.commons.exception.RepeatCommitException;
import com.openwjk.commons.utils.Constant;
import com.openwjk.commons.utils.EncryptUtil;
import com.openwjk.commons.utils.RandomCodeUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/10 14:33
 */
@Aspect
@Component
@Order(20)
@Log4j2
public class RepeatReqAspect extends AbstractAop {
    private static final ParameterNameDiscoverer DISCOVERER = new LocalVariableTableParameterNameDiscoverer();
    private static final String REPEAT_REQ = "REPEAT_REQ";
    @Autowired
    SpelHelper spelHelper;
    @Autowired
    RedisLockUtil redisLockUtil;

    @Around("@annotation(anni)")
    public Object aroundApi(ProceedingJoinPoint pjp, RepeatReq anni) throws Throwable {
        String key = getKey(pjp, anni);
        String value = RandomCodeUtil.generateCode(16);
        Object result;
        try {
            if (redisLockUtil.tryLock(key, value, anni.expire())) {
                result = pjp.proceed();
            } else {
                throw new RepeatCommitException(ResponseEnum.REPEAT_COMMIT.getMsg());
            }
        } finally {
            redisLockUtil.releaseLock(key, value);
        }
        return result;
    }

    private String getKey(ProceedingJoinPoint pjp, RepeatReq anno) throws NoSuchMethodException {
        String key;
        Method method = getTargetMethod(pjp);
        String[] paramNames = DISCOVERER.getParameterNames(method);
        Map<String, Object> param = new HashMap<>();
        Object[] args = pjp.getArgs();
        for (int len = 0; len < paramNames.length; len++) {
            param.put(paramNames[len], args[len]);
        }
        String[] spel = anno.key();
        key = spelHelper.getValue(spel, param);
        HttpServletRequest request = getRequestObj();
        String ip = IpUtil.getIp(request);
        if (StringUtils.isBlank(key)) {
            if (args.length > 0) {
                key = REPEAT_REQ + Constant.BOTTOM_LINE + EncryptUtil.md5(ip + getMethodAllPath(pjp) + JSON.toJSONString(args));

            } else {
                key = REPEAT_REQ + Constant.BOTTOM_LINE + EncryptUtil.md5(ip + getMethodAllPath(pjp));
            }
        } else {
            key = REPEAT_REQ + Constant.BOTTOM_LINE + EncryptUtil.md5(ip + getMethodAllPath(pjp) + key);
        }
        return key;
    }


    private HttpServletRequest getRequestObj() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        } catch (Exception e) {
            log.warn("call RepeatReqAspect.getRequestObj, occur exception");
            return null;
        }
    }

}
