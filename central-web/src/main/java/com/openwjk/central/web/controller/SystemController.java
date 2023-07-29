package com.openwjk.central.web.controller;

import com.openwjk.central.service.service.SystemService;
import com.openwjk.central.web.domain.SystemDomain;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wangjunkai
 * @description
 * @date 2023/7/21 13:24
 */
@RestController
@RequestMapping("/")
@Slf4j
@ApiOperation("系统控制层")
public class SystemController {

    @Autowired
    SystemService systemService;

    @GetMapping("/check.htm")
    @ApiOperation("检测系统是否运行")
    public String checkRun() {
        log.info("success.");
        return systemService.checkRun();
    }

    @PostMapping("/check")
    @ApiOperation("检测系统是否运行2")
    public String checkTest(@RequestBody SystemDomain domain) {
        log.info("success.");
        return systemService.checkRun();
    }
}
