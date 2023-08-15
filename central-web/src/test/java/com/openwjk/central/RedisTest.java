package com.openwjk.central;

import com.alibaba.fastjson2.JSON;
import com.openwjk.central.commons.domain.CommonQueryReqDTO;
import com.openwjk.central.commons.enums.QwAppEnum;
import com.openwjk.central.commons.utils.RedisUtil;
import com.openwjk.central.remote.dto.response.CommonQueryRespDTO;
import com.openwjk.central.remote.service.impl.qwapp.QwAppServiceImpl;
import com.openwjk.central.service.service.SystemService;
import com.openwjk.commons.utils.RandomCodeUtil;
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
public class RedisTest {
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    SystemService systemService;

    public static void main(String[] args) {
        System.out.println(RandomCodeUtil.getUuId());
    }

}
