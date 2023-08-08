package com.openwjk.central.service.service;

import com.openwjk.central.service.domain.req.WebhookReqVO;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/5 10:45
 */
public interface QwMsgService {
    void sendDdnsMsg(WebhookReqVO reqVO);
}
