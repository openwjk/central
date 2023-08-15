package com.openwjk.central.service.service;

import com.openwjk.central.service.domain.req.LoginAccountReqVO;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/15 9:48
 */
public interface AccountService {
    String registerAccount(LoginAccountReqVO reqVO,String type);

    String loginAccount(LoginAccountReqVO reqVO,String type);
}
