package com.openwjk.central;


import com.openwjk.central.service.domain.req.LoginAccountReqVO;
import com.openwjk.central.service.impl.SnowflakeService;
import com.openwjk.central.web.controller.AccountController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author wangjunkai
 * @description
 * @date 2023/7/29 8:09
 */
@SpringBootTest(classes = BaseTest.class)
@RunWith(SpringRunner.class)
public class LeafTest {
    @Autowired
    SnowflakeService snowflakeService;
    @Autowired
    AccountController accountController;

    @Test
    public void test() {
        for (Long i = 1L; i < 100; i++) {
            System.out.println(snowflakeService.getId());
        }
    }

    @Test
    public void accountTest() {
        for (Long i = 1L; i < 100; i++) {
            LoginAccountReqVO reqVO = new LoginAccountReqVO();
            reqVO.setAccount("ss" +i);
            reqVO.setPassword("cc"+i);
            accountController.register(reqVO);
        }
    }
}
