package com.openwjk.central.web.controller;

import com.alibaba.fastjson2.JSON;
import com.openwjk.central.service.domain.req.WebhookReqVO;
import com.openwjk.central.service.enums.WebhookEnum;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/6 19:43
 */
@RestController
@RequestMapping("/wx")
@ApiOperation("企业微信系统控制层")
@Log4j2
public class WxController {
    @PostMapping("/verify")
    @ApiOperation("检测系统是否运行")
    public void transpond() {

    }
}
