package com.openwjk.central.remote.dto.response;

import com.alibaba.fastjson2.annotation.JSONField;
import com.openwjk.central.commons.domain.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/4 10:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QwAppSendMsgRespDTO extends BaseDomain {
    @JSONField(name = "errcode")
    private String errCode;
    @JSONField(name = "errmsg")
    private String errMsg;
    @JSONField(name = "invaliduser")
    private String invalidUser;
    @JSONField(name = "invalidparty")
    private String invalidParty;
    @JSONField(name = "invalidtag")
    private String invalidTag;
    @JSONField(name = "unlicenseduser")
    private String unLicensedUser;
    @JSONField(name = "msgid")
    private String msgId;
    @JSONField(name = "response_code")
    private String responseCode;
}
