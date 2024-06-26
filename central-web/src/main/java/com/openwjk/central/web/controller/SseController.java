package com.openwjk.central.web.controller;

import com.alibaba.fastjson2.JSON;
import com.openwjk.central.commons.annotation.RepeatReq;
import com.openwjk.central.service.domain.req.LoginAccountReqVO;
import com.openwjk.central.service.service.AccountService;
import com.openwjk.central.web.handle.MsgHandler;
import com.openwjk.central.web.utils.CookieUtil;
import com.openwjk.central.web.utils.SSEClient;
import com.openwjk.central.web.utils.SSEUtils;
import com.openwjk.commons.domain.ResponseVO;
import com.openwjk.commons.utils.Constant;
import com.openwjk.commons.utils.DateUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.Date;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/14 18:18
 */
@RestController
@RequestMapping("/sse")
@ApiOperation("流式输出")
@Log4j2
public class SseController {
    @Autowired
    @Qualifier("pool")
    ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @RequestMapping(value = "/sub", method = RequestMethod.POST, produces = {MediaType.TEXT_EVENT_STREAM_VALUE})
    public SseEmitter subscribe(@RequestParam("id") String id) {
        log.info("accept_new_question,id={}", id);
        // 添加订阅,建立sse链接
        SseEmitter emitter = SSEUtils.addSub(id);
        threadPoolTaskExecutor.execute(() -> {
            try {
                for(int i=0;i<60;i++){
                    SSEUtils.pubMsg(id, "test", String.valueOf(DateUtil.formatDate(new Date(), DateUtil.FORMAT_DATETIME_NORMAL)), i);
                    Thread.sleep(1000);
                }
                //调用api中转到前端
//                InputStream inputStream = SSEClient.getSseInputStream("http://172.22.0.40:8088/v1/extract_pat_info");
//                SSEClient.readStream(inputStream, msg -> {
//                    if (StringUtils.isNotBlank(msg)) {
//                        SSEUtils.pubMsg(id, "", String.valueOf(DateUtil.formatDate(new Date(), DateUtil.FORMAT_DATETIME_NORMAL)), msg);
//                    }
//                });
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // 消息发送完关闭订阅
                SSEUtils.closeSub(id);
            }
        });
        return emitter;
    }
}
