package com.openwjk.central;

import com.alibaba.fastjson2.JSON;
import com.openwjk.central.commons.domain.CommonQueryReqDTO;
import com.openwjk.central.commons.enums.ComWechatAppEnum;
import com.openwjk.central.commons.utils.RedisUtil;
import com.openwjk.central.remote.dto.request.ComWcAppSendTextMsgReqDTO;
import com.openwjk.central.remote.dto.response.CommonQueryRespDTO;
import com.openwjk.central.remote.service.impl.comwcapp.ComWcAppServiceImpl;
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
public class ComWcAppTest {
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    SystemService systemService;
    @Autowired
    @Qualifier("comWcAppService")
    ComWcAppServiceImpl comWechatService;

    @Test
    public void appSendTextMsg() {
        ComWcAppSendTextMsgReqDTO reqDTO = new ComWcAppSendTextMsgReqDTO();
        reqDTO.setComWechatAppEnum(ComWechatAppEnum.NOTIFICATION);
        reqDTO.setToUser("@all");
        reqDTO.setMsgType("text");
        reqDTO.setAgentId("1000002");
        ComWcAppSendTextMsgReqDTO.Text text = new ComWcAppSendTextMsgReqDTO.Text();
        text.setContent("xxxxxxxxxxxxxxxxxx");
        reqDTO.setText(text);
        reqDTO.setSafe("1");
        CommonQueryRespDTO respDTO = comWechatService.appSendTextMsg(reqDTO);
        System.out.println(JSON.toJSONString(respDTO));
    }

    @Test
    public void test() {
        String id = RandomCodeUtil.generateCode(10);
        CommonQueryReqDTO reqDTO = new CommonQueryReqDTO(null, "simpleCache",id, "test");
        System.out.println(id);
        systemService.redisTest(reqDTO);
    }

}
