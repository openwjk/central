package com.openwjk.central.service.impl;

import com.openwjk.central.service.service.DubboTestService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/27 19:06
 */
@DubboService(interfaceClass = DubboTestService.class)
@Slf4j
public class DubboTestServiceImpl implements DubboTestService {
    @Override
    public String test() {
        log.info("DubboTestServiceImpl.test success ");
        return "success.";
    }
}
