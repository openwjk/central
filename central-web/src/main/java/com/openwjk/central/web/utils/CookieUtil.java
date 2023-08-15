package com.openwjk.central.web.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/15 18:39
 */
public class CookieUtil {

    /**
     * 添加cookie
     * @param response
     * @param name Key
     * @param value Value
     */
    public static void addCookie(HttpServletResponse response, String name, String value, HttpServletRequest request) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath(request.getContextPath());
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }

    /**
     * 检索所有Cookie封装到Map集合
     *
     * @param request
     * @return
     */
    public static Map<String, String> readCookieMap(HttpServletRequest request) {
        Map<String, String> cookieMap = new HashMap();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie.getValue());
            }
        }
        return cookieMap;
    }

    /**
     * 通过Key获取Value
     *
     * @param request
     * @param name Key
     * @return Value
     */
    public static String getCookieValueByName(HttpServletRequest request, String name) {
        Map<String, String> cookieMap = readCookieMap(request);
        if (cookieMap.containsKey(name)) {
            String cookieValue = cookieMap.get(name);
            return cookieValue;
        } else {
            return null;
        }
    }
}
