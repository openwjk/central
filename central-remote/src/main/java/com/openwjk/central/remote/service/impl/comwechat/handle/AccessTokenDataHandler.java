package com.openwjk.central.remote.service.impl.comwechat.handle;

import com.alibaba.fastjson2.JSON;
import com.openwjk.central.commons.domain.CacheableResultDTO;
import com.openwjk.central.commons.enums.CtConfigGroupEnum;
import com.openwjk.central.dao.model.CtConfigDO;
import com.openwjk.central.remote.dto.Context;
import com.openwjk.central.remote.dto.request.ComWechatRobotReqDTO;
import com.openwjk.central.remote.dto.request.RequestDTO;
import com.openwjk.central.remote.dto.response.ComWechatRobotRespDTO;
import com.openwjk.central.remote.enums.RemoteTypeEnum;
import com.openwjk.central.remote.helper.ConfigHelper;
import com.openwjk.central.remote.service.IDataService;
import com.openwjk.commons.utils.Constant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangjunkai
 * @description
 * @date 2023/7/30 11:00
 */
@Service
public class AccessTokenDataHandler implements IDataService {
    @Value("${comwechat.accessToken.url}")
    private String url;

    @Autowired
    ConfigHelper configHelper;

    @Override
    public RemoteTypeEnum getCode() {
        return RemoteTypeEnum.COM_WECHAT_ACCESS_TOKEN;
    }

    @Override
    public void buildRequest(Context context) {
        ComWechatRobotReqDTO robot = (ComWechatRobotReqDTO) context.getQueryDTO();
        RequestDTO requestDTO = new RequestDTO();
        Map<String, Object> map = new HashMap<>();
        Map<String, String> textMap = new HashMap<>();
        map.put("msgtype", "text");
        map.put("text", textMap);
        textMap.put("content", robot.getVerbalTrick());
        requestDTO.setBodyParam(JSON.toJSONString(map));
        CtConfigDO config = configHelper.getConfigByGroupAndCode(CtConfigGroupEnum.COM_WE_CHAT_APP.name(), robot.getRobotEnum().getCode());
        requestDTO.setUrl(config.getValue());
        context.setRequestDTO(requestDTO);
    }

    @Override
    public void setExpire(CacheableResultDTO resultDTO) {
        resultDTO.setExpire(7200L);
    }

    @Override
    public Boolean buildResponse(String resp) {
        if (StringUtils.isNotBlank(resp)) {
            ComWechatRobotRespDTO respDTO = JSON.parseObject(resp, ComWechatRobotRespDTO.class);
            if (resp != null && StringUtils.equals(Constant.STRING_ZERO, respDTO.getErrcode())) {
                return true;
            }
        }
        return false;
    }
}
