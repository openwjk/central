package com.openwjk.central.service.impl;

import com.openwjk.central.commons.annotation.ParamCondition;
import com.openwjk.central.service.handler.IVerifyHandler;
import com.openwjk.commons.exception.CommonsException;
import com.openwjk.commons.exception.ParamInvalidException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * @author wangjunkai
 * @description
 * @date 2023/12/21 15:35
 */
@Service
public class CheckParamService implements ApplicationContextAware {
    private static final ExpressionParser PARSER = new SpelExpressionParser();


    public void checkCondition(String spel, Map<String, Object> param) {
        if (StringUtils.isNotEmpty(spel)) {
            EvaluationContext context = new StandardEvaluationContext();
            for (Map.Entry<String, Object> entry : param.entrySet()) {
                context.setVariable(entry.getKey(), entry.getValue());
            }
            boolean invalid = checkCondition(spel, context);
            if (!invalid) {
                throw new ParamInvalidException(String.format("param invalid. param:[%s], spel:[%s]", param, spel));
            }
        }
    }

    private void checkCondition(String spel, String paramName, Object paramValue) {
        if (StringUtils.isNotEmpty(spel)) {
            EvaluationContext context = new StandardEvaluationContext();
            context.setVariable(paramName, paramValue);
            boolean invalid = checkCondition(spel, context);
            if (!invalid) {
                throw new ParamInvalidException(String.format("param invalid. [%s -> %s]", paramName, paramValue), paramName, paramValue);
            }
        }
    }

    private boolean checkCondition(String spel, EvaluationContext context) {
        Expression keyExpression = PARSER.parseExpression(spel);
        Boolean result = keyExpression.getValue(context, Boolean.class);
        return result;
    }

    public void checkComplex(Object complex) {
        if (complex == null) {
            return;
        }
        doCheck(complex);
    }
    private void doCheck(Object root) {

        Class<?> temClass = root.getClass();
        Field[] fields = new Field[0];
        while (temClass != null) {
            Field[] declaredFields = temClass.getDeclaredFields();
            fields = ArrayUtils.addAll(fields, declaredFields);
            temClass = temClass.getSuperclass();
        }

        for (Field field : fields) {
            String paramName = field.getName();
            ParamCondition anno = field.getAnnotation(ParamCondition.class);
            if (anno == null) {
                continue;
            }
            if (List.class.isAssignableFrom(field.getType())) {
                List<?> listChildParam = ((List<?>) getFieldValue(root, field));
                if (CollectionUtils.isEmpty(listChildParam)) {
                    if (anno.notEmpty()){
                        throw new ParamInvalidException(String.format("call CheckParamService.doCheck, paramName:%s is null.", paramName)
                                , paramName, "null");
                    }
                    continue;
                }
                int size = listChildParam.size();
                for (int i = 0; i < size; i++) {
                    Object childParamEntry = listChildParam.get(0);
                    if (null == childParamEntry) {
                        continue;
                    }
                    doCheck(childParamEntry);
                }
            } else {
                Object paramValue = getFieldValue(root, field);

                boolean notEmpty = anno.notEmpty();
                checkNotEmpty(notEmpty, paramName, paramValue);

                String condition = anno.condition();
                checkCondition(condition, paramName, paramValue);

                String handlerName = anno.verifyHandler();
                checkByHandler(handlerName, paramName, paramValue);

                boolean isComplex = anno.isComplex();
                if (isComplex) {
                    checkComplex(paramValue);
                }
            }
        }
    }

    private void checkNotEmpty(boolean notEmpty, String paramName, Object paramValue) {
        if (notEmpty) {
            if (paramValue == null) {
                throw new ParamInvalidException(String.format("call CheckParamService.checkNotEmpty, paramName:%s is null."
                        , paramName), paramName, "null");
            }
            if (paramValue instanceof String) {
                String temp = (String) paramValue;
                if (StringUtils.isBlank(temp)) {
                    throw new ParamInvalidException(String.format("call CheckParamService.checkNotEmpty, paramName:%s is empty."
                            , paramName), paramName, "null");
                }
            }

        }
    }

    private void checkByHandler(String handlerName, String paramName, Object paramValue) {
        if (StringUtils.isEmpty(handlerName)) {
            return;
        }
        boolean pass = ((IVerifyHandler) applicationContext.getBean(handlerName)).process(paramValue);
        if (!pass) {
            throw new ParamInvalidException(String.format("call CheckParamService.checkByHandler, handlerName:%s, param[%s -> %s] invalid."
                    , handlerName, paramName, paramValue), paramName, paramValue);
        }
    }

    private Object getFieldValue(Object param, Field field) {
        field.setAccessible(true);
        try {
            return field.get(param);
        } catch (IllegalAccessException e) {
            throw new CommonsException(String.format("call CheckParamService.getFieldValue, param:[%s], field:[%s]", param, field));
        }
    }

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
