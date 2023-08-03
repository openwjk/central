package com.openwjk.central.remote.service.impl.comwechat.handle;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.openwjk.central.commons.domain.CacheableResultDTO;
import com.openwjk.central.commons.enums.ComWechatAppEnum;
import com.openwjk.central.commons.enums.CtConfigGroupEnum;
import com.openwjk.central.dao.model.CtConfigDO;
import com.openwjk.central.remote.dto.Context;
import com.openwjk.central.remote.dto.request.ComWechatRobotReqDTO;
import com.openwjk.central.remote.dto.request.RequestDTO;
import com.openwjk.central.remote.dto.response.ComWechatAccessTokenRespDTO;
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
        ComWechatAppEnum appEnum = (ComWechatAppEnum) context.getQueryDTO();
        RequestDTO requestDTO = new RequestDTO();
        CtConfigDO config = configHelper.getConfigByGroupAndCode(CtConfigGroupEnum.COM_WE_CHAT_APP.name(), appEnum.getCode());
        Map<String, String> urlMap = JSONObject.parseObject(config.getValue(), Map.class);
        requestDTO.setUrlParam(urlMap);
        requestDTO.setUrl(url);
        context.setRequestDTO(requestDTO);
    }

    @Override
    public void setExpire(CacheableResultDTO resultDTO) {
        resultDTO.setExpire(7200L);
    }

    @Override
    public ComWechatAccessTokenRespDTO buildResponse(String resp) {
        if (StringUtils.isNotBlank(resp)) {
            ComWechatAccessTokenRespDTO respDTO = JSON.parseObject(resp, ComWechatAccessTokenRespDTO.class);
            return respDTO;
        }
        return null;
    }
}
