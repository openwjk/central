package com.openwjk.central.commons.utils;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author wangjunkai
 * @description
 * @date 2024/8/14 9:33
 */
@Slf4j
public class ReflectUtils {

    public List<Field> getAllFields(Class<?> clazz) {
        List<Field> fields = Lists.newArrayList();
        while (clazz != null) {
            for (Field field : clazz.getDeclaredFields()) {
                fields.add(field);
            }
            clazz = clazz.getSuperclass();
        }
        return fields;
    }

    private Object getFieldValue(Object param, Field field) {
        field.setAccessible(true);
        try {
            return field.get(param);
        } catch (Exception e) {
            log.error("get field error.", e);
        }
        return null;
    }

    private void setFieldValue(Object obj, Object value, Field field) {
        field.setAccessible(true);
        try {
            field.set(obj, value);
        } catch (Exception e) {
            log.error("set field error.", e);
        }
    }

}
