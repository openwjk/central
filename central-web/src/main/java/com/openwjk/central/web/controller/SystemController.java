package com.openwjk.central.web.controller;

import com.openwjk.central.commons.annotation.ApiLog;
import com.openwjk.central.service.service.SystemService;
import com.openwjk.commons.domain.ResponseVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wangjunkai
 * @description
 * @date 2023/7/21 13:24
 */
@RestController
@RequestMapping("/")
@ApiOperation("系统控制层")
@Log4j2
public class SystemController {
    @Autowired
    SystemService systemService;

    @GetMapping("/check.htm")
    @ApiOperation("检测系统是否运行")
    @ApiLog(standartReturn = false)
    public String checkRun() {
        log.info("success.");
        return systemService.checkRun();
    }

}
