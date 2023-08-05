package com.openwjk.central.remote.service.impl.qwapp.handle;

import com.alibaba.fastjson2.JSON;
import com.openwjk.central.commons.domain.CacheableResultDTO;
import com.openwjk.central.remote.dto.Context;
import com.openwjk.central.remote.dto.request.QwAppSendMarkdownReqDTO;
import com.openwjk.central.remote.dto.request.QwAppSendTextMsgReqDTO;
import com.openwjk.central.remote.dto.request.RequestDTO;
import com.openwjk.central.remote.dto.response.QwAppSendMsgRespDTO;
import com.openwjk.central.remote.enums.RemoteTypeEnum;
import com.openwjk.central.remote.helper.ConfigHelper;
import com.openwjk.central.remote.service.IDataService;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author wangjunkai
 * @description
 * @date 2023/7/30 11:00
 */
@Service
@Log4j2
public class AppSendMarkdownDataHandler implements IDataService {

    @Value("${qw.sendAppMsg.url}")
    private String url;

    @Autowired
    ConfigHelper configHelper;

    @Override
    public RemoteTypeEnum getCode() {
        return RemoteTypeEnum.QW_APP_NOTICE_MARKDOWN_MSG;
    }

    @Override
    public void buildRequest(Context context) {
        QwAppSendMarkdownReqDTO markdownReqDTO = (QwAppSendMarkdownReqDTO) context.getQueryDTO();
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setBodyParam(JSON.toJSONString(markdownReqDTO));
        requestDTO.setUrl(url + markdownReqDTO.getToken());
        context.setRequestDTO(requestDTO);
    }

    @Override
    public void enterCache(CacheableResultDTO resultDTO) {

    }

    @Override
    public QwAppSendMsgRespDTO buildResponse(String resp) {
        log.info("[{} response >>>>> {}]", RemoteTypeEnum.QW_APP_NOTICE_MARKDOWN_MSG.name(), resp);
        if (StringUtils.isNotBlank(resp)) {
            QwAppSendMsgRespDTO respDTO = JSON.parseObject(resp, QwAppSendMsgRespDTO.class);
            return respDTO;
        }
        return null;
    }
}
