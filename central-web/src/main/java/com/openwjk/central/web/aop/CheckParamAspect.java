package com.openwjk.central.web.aop;

import com.openwjk.central.commons.annotation.ApplyCheckParam;
import com.openwjk.central.commons.exception.ParamStrategyInvalidException;
import com.openwjk.central.service.factory.ParamStrategyFactory;
import com.openwjk.central.service.impl.CheckParamService;
import com.openwjk.central.service.impl.ParamReplacementService;
import com.openwjk.central.service.service.IParamStrategy;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangjunkai
 * @description
 * @date 2023/12/13 16:33
 */
@Aspect
@Component
@Order(30)
public class CheckParamAspect extends AbstractAop{
    private static final ParameterNameDiscoverer DISCOVERER = new LocalVariableTableParameterNameDiscoverer();
    @Autowired
    private CheckParamService checkParamService;
    @Autowired
    private ParamReplacementService paramReplacementService;
    @Autowired
    private ParamStrategyFactory strategyFactory;

    @Around("@annotation(anno)")
    public Object aroundApi(ProceedingJoinPoint pjp, ApplyCheckParam anno) throws Throwable {
        Object[] args = pjp.getArgs();
        Method method = getTargetMethod(pjp);

        Map<String, Object> param = new HashMap<>();
        String[] paramNames = DISCOVERER.getParameterNames(method);
        for (int len = 0; len < paramNames.length; len++) {
            param.put(paramNames[len], args[len]);
        }
        String conditionSpel = anno.briefness();
        checkParamService.checkCondition(conditionSpel, param);

        String[] complexs = anno.complex();
        if (complexs.length > 0) {
            for (String complex : complexs) {
                checkParamService.checkComplex(param.get(complex));
                paramReplacementService.replacement(param.get(complex));
            }
        }

        String strategy = anno.strategy();
        checkStrategy(strategy, param);

        return pjp.proceed(args);
    }

    private void checkStrategy(String primaryKey, Map<String, Object> param) {
        if (StringUtils.isEmpty(primaryKey) || param == null) {
            return;
        }
        IParamStrategy strategy = strategyFactory.getStrategy(primaryKey);
        boolean pass = strategy.determine(param);
        if (!pass) {
            throw new ParamStrategyInvalidException(String.format("the request strategy is invalid. primaryKey:[%s], requestParam:[%s]", primaryKey, param));
        }
    }
}
