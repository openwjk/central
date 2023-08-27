package com.openwjk.central.service.impl;

import com.openwjk.central.service.service.DubboTestService;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/27 19:06
 */
public class DubboTestServiceImpl implements DubboTestService {
    @Override
    public String test() {
        return "success.";
    }
}
