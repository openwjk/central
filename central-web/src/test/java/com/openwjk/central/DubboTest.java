package com.openwjk.central;

import com.openwjk.central.service.service.DubboTestService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/27 19:27
 */
@SpringBootTest(classes = BaseTest.class)
@RunWith(SpringRunner.class)
public class DubboTest {
    @DubboReference
    DubboTestService dubboTestService;
    @Test
    public void test() {
        System.out.println(dubboTestService.test());
        System.out.println(1);
    }
}
