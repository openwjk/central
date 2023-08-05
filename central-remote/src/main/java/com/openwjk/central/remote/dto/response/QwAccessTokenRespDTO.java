package com.openwjk.central.remote.dto.response;

import com.alibaba.fastjson2.annotation.JSONField;
import com.openwjk.central.commons.domain.BaseDomain;
import lombok.Data;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/3 21:25
 */
@Data
public class QwAccessTokenRespDTO extends BaseDomain {
    @JSONField(name = "errcode")
    private String errCode;
    @JSONField(name = "errmsg")
    private String errMsg;
    @JSONField(name = "access_token")
    private String accessToken;
    @JSONField(name = "expires_in")
    private String expiresIn;
}
