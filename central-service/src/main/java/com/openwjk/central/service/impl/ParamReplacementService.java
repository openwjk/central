package com.openwjk.central.service.impl;

import com.openwjk.central.commons.annotation.ParamReplacement;
import com.openwjk.central.commons.utils.TrimUtil;
import com.openwjk.commons.exception.CommonsException;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author wangjunkai
 * @description
 * @date 2023/12/21 15:41
 */
@Service
public class ParamReplacementService {
    private static final String STRING_PACKAGE = "java.lang.String";
    private static final String SET_METHOD_PREFIX = "set";

    public void replacement(Object complex) {
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
            ParamReplacement anno = field.getAnnotation(ParamReplacement.class);
            if (anno == null) {
                continue;
            }
            if (List.class.isAssignableFrom(field.getType())) {
                List<?> listChildParam = ((List<?>) getFieldValue(root, field));
                int size = listChildParam.size();
                for (int i = 0; i < size; i++) {
                    Object childParamEntry = listChildParam.get(0);
                    if (null == childParamEntry) {
                        continue;
                    }
                    doCheck(childParamEntry);
                }
            } else {
                String paramName = field.getName();
                Object paramValue = getFieldValue(root, field);
                if (paramValue == null) {
                    continue;
                }
                if (paramValue instanceof String) {
                    String replace = ((String) paramValue).replace(anno.regex(), anno.replacement());
                    try {
                        setFieldValue(root, paramName, replace);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    TrimUtil.trimAndReplacePunctuation(paramValue, anno.regex(), anno.replacement());
                }
            }
        }
    }

    private void setFieldValue(Object bean, String fieldName, Object value)
            throws Exception {
        String methodName = SET_METHOD_PREFIX +
                fieldName.substring(0, 1).toUpperCase() +
                fieldName.substring(1);

        @SuppressWarnings("rawtypes")
        Class[] classArr = new Class[1];
        classArr[0] = STRING_PACKAGE.getClass();
        Method method = bean.getClass().getMethod(methodName, classArr);
        method.invoke(bean, value);
    }

    private Object getFieldValue(Object param, Field field) {
        field.setAccessible(true);
        try {
            return field.get(param);
        } catch (IllegalAccessException e) {
            throw new CommonsException(String.format("call CheckParamService.getFieldValue, param:[%s], field:[%s]", param, field));
        }
    }
}
