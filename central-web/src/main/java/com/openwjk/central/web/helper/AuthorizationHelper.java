package com.openwjk.central.web.helper;

import com.openwjk.commons.utils.Constant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/17 15:02
 */
@Component
public class AuthorizationHelper {
    public String getToken(HttpServletRequest request) {
        String value = request.getHeader(Constant.COOKIE_KEY_TOKEN);
        if (StringUtils.isEmpty(value)) {
            value = getCookieValue(request, Constant.COOKIE_KEY_TOKEN);
        }
        return value;
    }

    public void saveToken(HttpServletRequest request, HttpServletResponse response, String tokenValue) {
        Cookie cookie = new Cookie(Constant.COOKIE_KEY_TOKEN, tokenValue);
        cookie.setPath(request.getContextPath());
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }


    public String getCookieValue(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (StringUtils.equals(cookie.getName(), key)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public String getCookieValueNotEmpty(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (StringUtils.equals(cookie.getName(), key)) {
                    if (StringUtils.isEmpty(cookie.getValue())) {
                        continue;
                    }
                    return cookie.getValue();
                }
            }
        }
        return null;
    }



}
