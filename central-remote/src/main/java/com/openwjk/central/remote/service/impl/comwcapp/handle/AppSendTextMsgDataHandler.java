package com.openwjk.central.remote.service.impl.comwcapp.handle;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.openwjk.central.commons.domain.CacheableResultDTO;
import com.openwjk.central.commons.enums.ComWechatAppEnum;
import com.openwjk.central.commons.enums.CtConfigGroupEnum;
import com.openwjk.central.dao.model.CtConfigDO;
import com.openwjk.central.remote.dto.Context;
import com.openwjk.central.remote.dto.request.ComWcAppSendTextMsgReqDTO;
import com.openwjk.central.remote.dto.request.RequestDTO;
import com.openwjk.central.remote.dto.response.ComWcAppSendTextMsgRespDTO;
import com.openwjk.central.remote.dto.response.ComWechatAccessTokenRespDTO;
import com.openwjk.central.remote.enums.RemoteTypeEnum;
import com.openwjk.central.remote.helper.ConfigHelper;
import com.openwjk.central.remote.service.IDataService;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author wangjunkai
 * @description
 * @date 2023/7/30 11:00
 */
@Service
@Log4j2
public class AppSendTextMsgDataHandler implements IDataService {

    @Value("${comwechat.sendAppMsg.url}")
    private String url;

    @Autowired
    ConfigHelper configHelper;

    @Override
    public RemoteTypeEnum getCode() {
        return RemoteTypeEnum.COM_WECHAT_APP_NOTICE_TEXT_MSG;
    }

    @Override
    public void buildRequest(Context context) {
        ComWcAppSendTextMsgReqDTO textMsgReqDTO = (ComWcAppSendTextMsgReqDTO) context.getQueryDTO();
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setBodyParam(JSON.toJSONString(textMsgReqDTO));
        requestDTO.setUrl(url + textMsgReqDTO.getToken());
        context.setRequestDTO(requestDTO);
    }

    @Override
    public void enterCache(CacheableResultDTO resultDTO) {

    }

    @Override
    public ComWcAppSendTextMsgRespDTO buildResponse(String resp) {
        log.info("[{} response >>>>> {}]", RemoteTypeEnum.COM_WECHAT_APP_NOTICE_TEXT_MSG.name(), resp);
        if (StringUtils.isNotBlank(resp)) {
            ComWcAppSendTextMsgRespDTO respDTO = JSON.parseObject(resp, ComWcAppSendTextMsgRespDTO.class);
            return respDTO;
        }
        return null;
    }
}
