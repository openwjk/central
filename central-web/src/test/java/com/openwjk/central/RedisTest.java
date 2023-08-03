package com.openwjk.central;

import com.openwjk.central.commons.domain.CommonQueryReqDTO;
import com.openwjk.central.commons.utils.RedisUtil;
import com.openwjk.central.commons.domain.BaseDomain;
import com.openwjk.central.service.domain.BirthDayDomain;
import com.openwjk.central.service.service.SystemService;
import com.openwjk.commons.utils.RandomCodeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
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
    @Autowired
    @Qualifier("pool")
    ThreadPoolTaskExecutor taskExecutor;

    @Test
    public void test() {
        for(int i=0;i<100000;i++){
            String id=RandomCodeUtil.generateCode(10);
            CommonQueryReqDTO reqDTO = new CommonQueryReqDTO(null,id,"test");
            System.out.println(id);
            systemService.redisTest(reqDTO);
        }
    }

    public static void main(String[] args) {
        BirthDayDomain dayDomain = new BirthDayDomain();
        System.out.println(dayDomain instanceof BaseDomain);
    }


}
