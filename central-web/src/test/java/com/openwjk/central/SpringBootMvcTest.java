package com.openwjk.central;


import com.openwjk.central.service.domain.req.DdnsWebhookReqVO;
import com.openwjk.central.web.controller.MsgTranspondController;
import com.openwjk.central.web.controller.SystemController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletResponse;

/**
 * @author wangjunkai
 * @description
 * @date 2023/7/29 8:09
 */
@SpringBootTest(classes = BaseTest.class)
@RunWith(SpringRunner.class)
public class SpringBootMvcTest {
    @Autowired
    SystemController systemController;
    @Autowired
    MsgTranspondController msgTranspondController;

    @Test
    public void test() {
        System.out.println(systemController.checkRun());
    }

    @Test
    public void aopTest() {
        DdnsWebhookReqVO reqVO = new DdnsWebhookReqVO();
        DdnsWebhookReqVO.Content content = new DdnsWebhookReqVO.Content();
        content.setContent("xxx");
        reqVO.setText(content);
        reqVO.setKey("123");
        reqVO.setMsgType("text");
        reqVO.setToUser("@all");
        msgTranspondController.transpond(reqVO, new MockHttpServletResponse());
    }
}
