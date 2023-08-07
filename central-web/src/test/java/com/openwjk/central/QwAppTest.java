package com.openwjk.central;

import com.alibaba.fastjson2.JSON;
import com.openwjk.central.commons.enums.QwAppEnum;
import com.openwjk.central.commons.utils.RedisUtil;
import com.openwjk.central.remote.dto.request.BaseQwAppSendMsgReqDTO;
import com.openwjk.central.remote.dto.request.QwAppSendMarkdownReqDTO;
import com.openwjk.central.remote.dto.request.QwAppSendTextMsgReqDTO;
import com.openwjk.central.remote.dto.response.CommonQueryRespDTO;
import com.openwjk.central.remote.service.impl.qwapp.QwAppServiceImpl;
import com.openwjk.central.service.service.SystemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/2 10:22
 */
@SpringBootTest(classes = BaseTest.class)
@RunWith(SpringRunner.class)
public class QwAppTest {
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    SystemService systemService;
    @Autowired
    @Qualifier("qwAppService")
    QwAppServiceImpl qwAppService;


    @Test
    public void getAccessToken() {
        CommonQueryRespDTO respDTO = qwAppService.getAppAccessToken(QwAppEnum.NOTIFICATION);
        System.out.println(JSON.toJSONString(respDTO));
    }

    @Test
    public void appSendTextMsg() {
        QwAppSendTextMsgReqDTO reqDTO = new QwAppSendTextMsgReqDTO();
        reqDTO.setQwAppEnum(QwAppEnum.NOTIFICATION);
        reqDTO.setToUser("WangJunKai");
        reqDTO.setMsgType("text");
        reqDTO.setAgentId("1000002");
        QwAppSendTextMsgReqDTO.Content text = new QwAppSendTextMsgReqDTO.Content();
        text.setContent("xxxxxxxxxxxxxxxxxx");
        reqDTO.setText(text);
        reqDTO.setSafe("1");
        CommonQueryRespDTO respDTO = qwAppService.appSendTextMsg(reqDTO);
        System.out.println(JSON.toJSONString(respDTO));
    }

    @Test
    public void appSendMarkdown() {
        QwAppSendMarkdownReqDTO reqDTO = new QwAppSendMarkdownReqDTO();
        reqDTO.setQwAppEnum(QwAppEnum.NOTIFICATION);
        reqDTO.setToUser("WangJunKai");
        reqDTO.setMsgType("markdown");
        reqDTO.setAgentId("1000002");
        BaseQwAppSendMsgReqDTO.Content markdown = new BaseQwAppSendMsgReqDTO.Content();
        markdown.setContent("# 123\n" +
                "<font color=\"red\">red</font>");
        reqDTO.setMarkdown(markdown);
        CommonQueryRespDTO respDTO = qwAppService.appSendMarkdown(reqDTO);
        System.out.println(JSON.toJSONString(respDTO));
    }

}
