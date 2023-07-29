package com.openwjk.central;

import com.openwjk.central.service.service.SystemService;
import com.openwjk.central.web.controller.SystemController;
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
public class SpringBootMvcTest {
    @Autowired
    SystemController systemController;
    @Test
    public void test(){
        System.out.println(systemController.checkRun());
    }
}
