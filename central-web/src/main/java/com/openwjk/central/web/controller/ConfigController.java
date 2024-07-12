package com.openwjk.central.web.controller;

import com.openwjk.central.commons.annotation.RepeatReq;
import com.openwjk.central.service.domain.req.CtConfigReqVO;
import com.openwjk.central.service.domain.req.LoginAccountReqVO;
import com.openwjk.central.service.impl.CtConfigService;
import com.openwjk.commons.domain.ResponseVO;
import com.openwjk.commons.utils.Constant;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/14 18:18
 */
@RestController
@RequestMapping("/config")
@ApiOperation("配置")
@Log4j2
public class ConfigController {
    @Autowired
    CtConfigService configService;

    @PostMapping("/add")
    @ApiOperation("新增配置")
    public ResponseVO<String> register(CtConfigReqVO reqVO) {
        configService.addConfig(reqVO);
        return new ResponseVO();
    }
}
