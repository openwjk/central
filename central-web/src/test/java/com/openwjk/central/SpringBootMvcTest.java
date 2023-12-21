package com.openwjk.central;


import com.openwjk.central.service.domain.req.DdnsWebhookReqVO;
import com.openwjk.central.service.domain.req.LoginAccountReqVO;
import com.openwjk.central.web.controller.AccountController;
import com.openwjk.central.web.controller.MsgTranspondController;
import com.openwjk.central.web.controller.SystemController;
import com.openwjk.commons.utils.RandomCodeUtil;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author wangjunkai
 * @description
 * @date 2023/7/29 8:09
 */
@SpringBootTest(classes = BaseTest.class)
@RunWith(SpringRunner.class)
@Log4j2
public class SpringBootMvcTest {
    @Autowired
    SystemController systemController;
    @Autowired
    MsgTranspondController msgTranspondController;
    @Autowired
    @Qualifier("pool")
    ThreadPoolTaskExecutor poolTaskExecutor;
    @Autowired
    AccountController accountController;

    @Test
    public void test() {
        System.out.println(systemController.checkRun());
    }

    @Test
    public void registerTest() {
        for (int i = 0; i < 100; i++) {
            LoginAccountReqVO reqVO = new LoginAccountReqVO();
            reqVO.setAccount(RandomCodeUtil.generateCode(10));
            reqVO.setPassword(RandomCodeUtil.generateCode(10));
            accountController.register(reqVO);
        }

    }

    @Test
    public void aopTest() {
        DdnsWebhookReqVO reqVO = new DdnsWebhookReqVO();
        DdnsWebhookReqVO.Content content = new DdnsWebhookReqVO.Content();
        content.setContent("老子明天不上班，巴适的板，巴适得板");
        reqVO.setText(content);
        reqVO.setKey("AE1A71CEAB454A428B6AB5E29999DCFD");
        reqVO.setMsgType("text");
        reqVO.setToUser("@all");
        for (int i = 0; i < 10; i++) {
            poolTaskExecutor.execute(() -> {
                log.info(msgTranspondController.transpond(reqVO, new MockHttpServletResponse()));
            });
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
