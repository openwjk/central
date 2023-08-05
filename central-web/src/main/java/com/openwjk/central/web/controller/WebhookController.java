package com.openwjk.central.web.controller;

import com.alibaba.fastjson2.JSON;
import com.openwjk.central.service.enums.WebhookEnum;
import com.openwjk.central.service.domain.req.WebhookReqVO;
import com.openwjk.central.service.service.WebhookService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author wangjunkai
 * @description
 * @date 2023/7/21 13:24
 */
@RestController
@RequestMapping("/wh")
@ApiOperation("webhook转发")
@Log4j2
public class WebhookController {

    @Autowired
    WebhookService webhookService;

    @PostMapping("/transpond")
    @ApiOperation("检测系统是否运行")
    public void transpond(@RequestBody WebhookReqVO reqVO, HttpServletResponse response) throws IOException {

        if (!WebhookEnum.contains(reqVO.getKey()) || reqVO == null || reqVO.getText() == null
                || StringUtils.isBlank(reqVO.getText().getContent())) {
            response.setStatus(403);
        }
        log.info(JSON.toJSONString(reqVO));
        webhookService.sendDdnsMsg(reqVO);
        log.info("success.");
    }
}
