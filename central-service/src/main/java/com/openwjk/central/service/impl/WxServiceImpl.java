package com.openwjk.central.service.impl;

import com.google.common.collect.Lists;
import com.openwjk.central.commons.utils.wx.Sha;
import com.openwjk.central.service.service.WxService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/7 10:50
 */
public class WxServiceImpl implements WxService {

    @Override
    public boolean verify(String signature, String timestamp, String nonce) {
        //TOKEN为微信页面上token
        List<String> list = Lists.newArrayList(timestamp, nonce, "TOKEN");
        list = list.stream().sorted().collect(Collectors.toList());
        list.stream().collect(Collectors.joining());
        String str = list.stream().collect(Collectors.joining());
        // 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
        return signature.equals(Sha.encryptSHA(str));
    }
}
