package com.openwjk.central.web.controller;

import com.alibaba.fastjson2.JSON;
import com.openwjk.central.service.enums.WebhookEnum;
import com.openwjk.central.service.domain.req.WebhookReqVO;
import com.openwjk.central.service.service.QwMsgService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


/**
 * @author wangjunkai
 * @description
 * @date 2023/7/21 13:24
 */
@RestController
@RequestMapping("/msg")
@ApiOperation("消息转发控制层")
@Log4j2
public class MsgTranspondController {

    @Autowired
    QwMsgService qwAppMsgService;

    @PostMapping("/ddns/transpond")
    @ApiOperation("ddns预警消息转发")
    public void transpond(@RequestBody WebhookReqVO reqVO, HttpServletResponse response) {

        if (!WebhookEnum.contains(reqVO.getKey()) || reqVO == null || reqVO.getText() == null
                || StringUtils.isBlank(reqVO.getText().getContent())) {
            response.setStatus(403);
        }
        log.info(JSON.toJSONString(reqVO));
        qwAppMsgService.sendDdnsMsg(reqVO);
        log.info("success.");
    }
}
