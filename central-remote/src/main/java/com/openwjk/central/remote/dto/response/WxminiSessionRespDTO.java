package com.openwjk.central.remote.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author wangjunkai
 * @description
 * @date 2023/7/30 10:49
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WxminiSessionRespDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String sessionKey;
    private String unionid;
    private String errmsg;
    private String openid;
    private String errcode;
}
