package com.openwjk.central.commons.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/17 15:55
 */
@Data
public class LoginAccountDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String token;
    private String uId;
    private String account;
    private String type;
}
