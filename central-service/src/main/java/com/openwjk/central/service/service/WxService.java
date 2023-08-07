package com.openwjk.central.service.service;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/7 10:50
 */
public interface WxService {
    /**
     * 微信公众号服务器校验
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    boolean verify(String signature, String timestamp, String nonce);
}
