package com.openwjk.central.web.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/9 14:59
 */
public class IpUtil {
    public static String getIp(HttpServletRequest request) {
        if (null == request) {
            return "";
        }
        String ip = request.getHeader("X-Real-IP");
        if(isUnknownIp(ip)){
            ip = request.getHeader("X-Forwarded-For");
        }
        if (isUnknownIp(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (isUnknownIp(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (isUnknownIp(ip)) {
            ip = request.getRemoteAddr();
        }

        if (StringUtils.isNotBlank(ip)) {
            String[] ips = ip.split(",");
            if (ips.length > 0) {
                ip = ips[0];
            }
        }
        return ip;
    }

    private static boolean isUnknownIp(String ip) {
        return ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip);
    }
}
