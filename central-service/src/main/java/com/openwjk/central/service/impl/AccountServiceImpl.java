package com.openwjk.central.service.impl;

import com.openwjk.central.service.domain.req.LoginAccountReqVO;
import com.openwjk.central.service.service.AccountService;
import com.openwjk.commons.enums.ResponseEnum;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/15 9:52
 */
public class AccountServiceImpl implements AccountService {
    @Override
    public String registerAccount(LoginAccountReqVO reqVO) {

        return ResponseEnum.SUCCESS.getMsg();
    }

    @Override
    public String loginAccount(LoginAccountReqVO reqVO) {
        return null;
    }
}
