package com.openwjk.central.service.domain.req;

import com.openwjk.central.commons.domain.BaseDomain;
import lombok.Data;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/15 9:46
 */
@Data
public class LoginAccountReqVO extends BaseDomain {
    private String account;
    private String password;
}
