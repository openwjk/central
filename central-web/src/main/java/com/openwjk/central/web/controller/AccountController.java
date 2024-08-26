package com.openwjk.central.web.controller;

import com.openwjk.central.commons.annotation.ApplyCheckParam;
import com.openwjk.central.commons.annotation.RepeatReq;
import com.openwjk.central.service.domain.req.LoginAccountReqVO;
import com.openwjk.central.service.service.AccountService;
import com.openwjk.central.web.utils.CookieUtil;
import com.openwjk.commons.domain.ResponseVO;
import com.openwjk.commons.utils.Constant;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/14 18:18
 */
@RestController
@RequestMapping("/account")
@ApiOperation("账户控制层")
@Log4j2
public class AccountController {
    @Autowired
    AccountService accountService;

    @PostMapping("/login")
    @ApiOperation("账户登录")
    @ApplyCheckParam(complex = "reqVo")
    public ResponseVO<String> login(LoginAccountReqVO reqVO, HttpServletRequest request, HttpServletResponse response) {
        String token = accountService.loginAccount(reqVO, Constant.DEFAULT);
        CookieUtil.addCookie(response, Constant.COOKIE_KEY_TOKEN, token, request);
        return new ResponseVO(token);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ApiOperation(value = "用户登出", notes = "GET请求", httpMethod = "GET")
    public ResponseVO logout(HttpServletRequest request, HttpServletResponse response) {
        accountService.logout(CookieUtil.getCookieValueByName(request,Constant.COOKIE_KEY_TOKEN));
        //清空cookie中的token
        CookieUtil.addCookie(response, Constant.COOKIE_KEY_TOKEN, null, request);
        return new ResponseVO();
    }

    @PostMapping("/register")
    @ApiOperation("账户注册")
    @RepeatReq(key = {"#reqVO.account"})
    public ResponseVO<String> register(LoginAccountReqVO reqVO) {
        return new ResponseVO(accountService.registerAccount(reqVO, Constant.DEFAULT));
    }

    @PostMapping("/getAuthCode")
    @ApiOperation("获取验证码")
    public ResponseVO getAuthCode(LoginAccountReqVO reqVO, HttpServletRequest request, HttpServletResponse response) {
        accountService.getAuthCode(reqVO, Constant.DEFAULT);
        return new ResponseVO();
    }

    @PostMapping("/authCode/login")
    @ApiOperation("账号验证码登录")
    @ApplyCheckParam(complex = "reqVo")
    public ResponseVO<String> authCodeLogin(LoginAccountReqVO reqVO, HttpServletRequest request, HttpServletResponse response) {
        String token = accountService.verifyAuthCode(reqVO, Constant.DEFAULT);
        CookieUtil.addCookie(response, Constant.COOKIE_KEY_TOKEN, token, request);
        return new ResponseVO(token);
    }
}
