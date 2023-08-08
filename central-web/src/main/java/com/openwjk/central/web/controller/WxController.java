package com.openwjk.central.web.controller;

import com.openwjk.central.service.service.WxService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/6 19:43
 */
@RestController
@RequestMapping("/wx")
@ApiOperation("微信系统控制层")
@Log4j2
public class WxController {
    @Autowired
    private WxService wxService;

    @GetMapping("/verify")
    public String verify(@RequestParam("signature") String signature,
                         @RequestParam("timestamp") String timestamp,
                         @RequestParam("nonce") String nonce,
                         @RequestParam("echostr") String echostr) {
        log.info("signature: {} nonce: {} echostr: {} timestamp: {}", signature, nonce, echostr, timestamp);
        if (wxService.verify(signature, timestamp, nonce)) {
            return echostr;
        }
        return "success.";
    }
}
