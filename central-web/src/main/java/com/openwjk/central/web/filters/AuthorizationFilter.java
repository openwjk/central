package com.openwjk.central.web.filters;


import com.openwjk.central.commons.domain.LoginAccountDTO;
import com.openwjk.central.commons.utils.Constants;
import com.openwjk.central.commons.utils.RedisUtil;
import com.openwjk.central.commons.utils.ThreadLocalUtil;
import com.openwjk.central.web.helper.AuthorizationHelper;
import com.openwjk.central.web.helper.IgnoreUrlHelper;
import com.openwjk.commons.enums.ResponseEnum;
import com.openwjk.commons.exception.IllegalAccessException;
import com.openwjk.commons.exception.UnauthorizedException;
import com.openwjk.commons.utils.Constant;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/17 14:26
 */
@Component
@Log4j2
public class AuthorizationFilter implements Filter {
    private List<String> ignoreUrls;
    @Autowired
    IgnoreUrlHelper ignoreUrlHelper;
    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;
    @Value("${auth.enable}")
    private String authEnable;
    @Value("${auth.ignore-url}")
    private String ignoreUrl;
    @Autowired
    AuthorizationHelper authorizationHelper;
    @Autowired
    RedisUtil redisUtil;

    @Override
    public void init(FilterConfig filterConfig) {
        ignoreUrls = ignoreUrlHelper.initIgnoreUrls(ignoreUrl);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        try {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            //是否开启url校验
            if (StringUtils.equals(Constant.STRING_ONE, authEnable)) {
                try {
                    if (!ignoreUrlHelper.isIgnoreUrl(httpServletRequest, ignoreUrls)) {
                        doTokenHandle(httpServletRequest);
                    }
                    chain.doFilter(request, response);
                } catch (IllegalAccessException | UnauthorizedException e) {
                    log.info(e.getMessage(), e);
                    wrapUnauthException(httpServletRequest);
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                    wrapUnauthException(httpServletRequest);
                }
            }

        } catch (UnauthorizedException | IllegalAccessException e) {
            log.info(e.getMessage());
            resolver.resolveException((HttpServletRequest) request, (HttpServletResponse) response, null, e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        // 清理资源
        cleanIfNeed();
    }

    private void cleanIfNeed() {
        ThreadLocalUtil.removeLoginDto();
    }

    private void wrapUnauthException(HttpServletRequest request) {
        throw new UnauthorizedException(
                ResponseEnum.UNAUTHORIZED.getMsg(),
                ResponseEnum.UNAUTHORIZED.getCode(),
                "登录失效请重新登录"
        );

    }

    private void doTokenHandle(HttpServletRequest request) {
        // 验证当前应用的用户登录态
        String token = authorizationHelper.getToken(request);
        String uri = request.getRequestURI();
        String tokenValue = verifyToken(uri, token);
        saveContext(token, tokenValue);
        redisUtil.expire(token, Constant.LONG_TEN, TimeUnit.MINUTES);
    }

    private void saveContext(String token, String tokenValue) {
        LoginAccountDTO loginAccountDTO = new LoginAccountDTO();
        loginAccountDTO.setToken(token);
        String[] arr = tokenValue.split(Constant.SPLIT_UNION);
        loginAccountDTO.setUId(arr[0]);
        loginAccountDTO.setAccount(arr[1]);
        loginAccountDTO.setType(arr[2]);
        //登录状态续期
        ThreadLocalUtil.setLoginDto(loginAccountDTO);
    }

    private String verifyToken(String uri, String token) {
        if (StringUtils.isEmpty(token)) {
            throw new IllegalAccessException(String.format("uri:[%s], token is empty.", uri));
        }
        if (!redisUtil.hasKey(token)) {
            throw new UnauthorizedException(String.format("uri:[%s], token:[%s] is invalid.", uri, token));
        }
        return redisUtil.get(token);
    }

    @Override
    public void destroy() {

    }
}
