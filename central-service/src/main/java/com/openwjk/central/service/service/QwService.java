package com.openwjk.central.service.service;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/6 19:50
 */
public interface QwService {
    /**
     * 企业应用服务器校验
     * @param msg_signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     */
    String verify(String msg_signature, String timestamp, String nonce, String echostr);
}
