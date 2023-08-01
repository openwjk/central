package com.openwjk.central.commons.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/1 15:08
 */
public class CacheableUtil {
    public static String buildCacheKey(String... args) {
        if (args == null || args.length == 0) {
            return StringUtils.EMPTY;
        }
        StringBuilder key = new StringBuilder();
        for (String arg : args) {
            key.append(arg);
            key.append("_");
        }
        if (key.length() >= 1) {
            key.deleteCharAt(key.length() - 1);
        }
        return key.toString();
    }

    public static String buildCacheKeyNotNull(String... args) {
        if (args == null || args.length == 0) {
            return StringUtils.EMPTY;
        }
        StringBuilder key = new StringBuilder();
        for (String arg : args) {
            if (StringUtils.isNotBlank(arg)) {
                key.append(arg);
                key.append("_");
            }
        }
        if (key.length() >= 1) {
            key.deleteCharAt(key.length() - 1);
        }
        return key.toString();
    }
}
