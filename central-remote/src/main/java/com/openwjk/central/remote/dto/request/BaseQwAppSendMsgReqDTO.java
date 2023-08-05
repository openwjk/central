package com.openwjk.central.remote.dto.request;

import com.alibaba.fastjson2.annotation.JSONField;
import com.openwjk.central.commons.domain.BaseDomain;
import com.openwjk.central.commons.enums.QwAppEnum;
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
public class BaseQwAppSendMsgReqDTO extends BaseDomain {
    @JSONField(name = "touser")
    private String toUser;
    @JSONField(name = "toparty")
    private String toParty;
    @JSONField(name = "totag")
    private String toTag;
    @JSONField(name = "msgtype")
    private String msgType;
    @JSONField(name = "agentid")
    private String agentId;
    @JSONField(name = "enable_duplicate_check")
    private String enableDuplicateCheck;
    @JSONField(name = "duplicate_check_interval")
    private String duplicateCheckInterval;
    @JSONField(serialize = false)
    private QwAppEnum qwAppEnum;
    @JSONField(serialize = false)
    private String token;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Content extends BaseDomain{
        @JSONField(name = "content")
        private String content;
    }
}
