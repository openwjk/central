package com.openwjk.central.commons.utils;

import com.openwjk.commons.exception.CommonsException;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wangjunkai
 * @description
 * @date 2023/12/21 15:42
 */
public class TrimUtil {
    private static final String STRING_PACKAGE = "java.lang.String";
    private static final String GET_METHOD_PREFIX = "get";
    private static final String SET_METHOD_PREFIX = "set";
    private static final Pattern REG_BLANK_PATTERN = Pattern.compile("[\\s*|\t|\r|\n]");

    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Matcher m = REG_BLANK_PATTERN.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    /**
     * 去掉bean中所有属性为字符串的前后空格
     *
     * @param bean
     * @throws Exception
     */
    public static void trim(Object bean) {
        try {
            if (bean != null) {
                Field[] fields = bean.getClass().getDeclaredFields();
                for (Field f : fields) {
                    if (f.getType().getName().equals(STRING_PACKAGE)) {
                        String fieldName = f.getName();
                        Object value = getFieldValue(bean, fieldName);

                        if (value == null) {
                            continue;
                        }
                        setFieldValue(bean, fieldName, value.toString().trim());
                    }
                }
            }
        } catch (Exception e) {
            throw new CommonsException(String.format("call TrimUtil.beanAttributeValueTrim occur exception. bean:[%s]", bean.toString()));
        }
    }

    public static void trimAndReplacePunctuation(Object bean, String scan, String replace) {
        try {
            if (bean != null) {
                Field[] fields = bean.getClass().getDeclaredFields();
                for (Field f : fields) {
                    if (f.getType().getName().equals(STRING_PACKAGE)) {
                        String key = f.getName();
                        Object value = getFieldValue(bean, key);

                        if (value == null) {
                            continue;
                        }
                        String newValue = value.toString().trim();
                        newValue = StringUtils.contains(newValue, scan) ? newValue.replace(scan, replace) : newValue;
                        setFieldValue(bean, key, newValue);
                    }
                }
            }
        } catch (Exception e) {
            throw new CommonsException(String.format("call TrimUtil.beanAttributeValueTrim occur exception. bean:[%s]", bean.toString()));
        }
    }



    /**
     * 利用反射通过get方法获取bean中字段fieldName的值
     *
     * @param bean
     * @param fieldName
     * @return
     * @throws Exception
     */
    private static Object getFieldValue(Object bean, String fieldName)
            throws Exception {
        String methodName = GET_METHOD_PREFIX +
                fieldName.substring(0, 1).toUpperCase() +
                fieldName.substring(1);

        Object rObject = null;
        Method method = null;

        method = bean.getClass().getMethod(methodName);
        rObject = method.invoke(bean);

        return rObject;
    }

    /**
     * 利用反射调用bean.set方法将value设置到字段
     *
     * @param bean
     * @param fieldName
     * @param value
     * @throws Exception
     */
    private static void setFieldValue(Object bean, String fieldName, Object value)
            throws Exception {
        String methodName = SET_METHOD_PREFIX +
                fieldName.substring(0, 1).toUpperCase() +
                fieldName.substring(1);

        /**
         * 利用发射调用bean.set方法将value设置到字段
         */
        @SuppressWarnings("rawtypes")
        Class[] classArr = new Class[1];
        classArr[0] = STRING_PACKAGE.getClass();
        Method method = bean.getClass().getMethod(methodName, classArr);
        method.invoke(bean, value);
    }
}
