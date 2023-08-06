package com.openwjk.central.web.controller;

import com.openwjk.central.commons.utils.qw.AesException;
import com.openwjk.central.commons.utils.qw.WXBizJsonMsgCrypt;
import com.openwjk.central.service.service.QwService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * @author wangjunkai
 * @description
 * @date 2023/7/21 13:24
 */
@RestController
@RequestMapping("/qw")
@ApiOperation("企业微信系统控制层")
@Log4j2
public class QwController {

    @Autowired
    QwService qwService;
    @PostMapping("/verify")
    @ApiOperation("企业微信服务器校验")
    public void verify(@RequestParam("msg_signature") String msg_signature,
                       @RequestParam("timestamp") String timestamp,
                       @RequestParam("nonce") String nonce,
                       @RequestParam("echostr") String echostr, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            out = response.getWriter();
            // 通过检验msg_signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
            String result = qwService.verify(msg_signature,timestamp,nonce,echostr);
            out.print(result);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            out.close();
            out = null;
        }
    }
}
