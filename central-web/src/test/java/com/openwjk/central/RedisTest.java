package com.openwjk.central;

import com.openwjk.central.commons.utils.RedisUtil;
import com.openwjk.central.service.service.SystemService;
import com.openwjk.commons.utils.RandomCodeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/2 10:22
 */
@SpringBootTest(classes = BaseTest.class)
@RunWith(SpringRunner.class)
public class RedisTest {
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    SystemService systemService;

    @Test
    public void test() {
        String id=RandomCodeUtil.generateCode(10);
        System.out.println(id);
        systemService.redisTest(id);
    }

}
