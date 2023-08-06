package com.openwjk.central.service.service;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/6 19:50
 */
public interface QwService {
    String verify(String msg_signature, String timestamp, String nonce, String echostr);
}
