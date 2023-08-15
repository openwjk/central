package com.openwjk.central.web.controller;

import com.openwjk.central.commons.annotation.RepeatReq;
import com.openwjk.central.service.domain.req.LoginAccountReqVO;
import com.openwjk.central.service.service.AccountService;
import com.openwjk.commons.domain.ResponseVO;
import com.openwjk.commons.utils.Constant;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseVO<String> login(LoginAccountReqVO reqVO) {
        return new ResponseVO(accountService.loginAccount(reqVO, Constant.DEFAULT));
    }

    @PostMapping("/register")
    @ApiOperation("账户注册")
    @RepeatReq(key = {"#reqVO.account"})
    public ResponseVO<String> register(LoginAccountReqVO reqVO) {
        return new ResponseVO(accountService.registerAccount(reqVO, Constant.DEFAULT));
    }
}
