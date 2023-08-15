package com.openwjk.central.remote.service.impl.qwapp.handle;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.openwjk.central.commons.domain.CacheableResultDTO;
import com.openwjk.central.commons.enums.QwAppEnum;
import com.openwjk.central.commons.enums.CtConfigGroupEnum;
import com.openwjk.central.dao.model.ConfigDO;
import com.openwjk.central.remote.dto.Context;
import com.openwjk.central.remote.dto.request.RequestDTO;
import com.openwjk.central.remote.dto.response.QwAccessTokenRespDTO;
import com.openwjk.central.remote.enums.RemoteTypeEnum;
import com.openwjk.central.remote.helper.ConfigHelper;
import com.openwjk.central.remote.service.IDataService;
import com.openwjk.commons.utils.Constant;
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
public class AccessTokenDataHandler implements IDataService {

    @Value("${qw.accessToken.url}")
    private String url;

    @Autowired
    ConfigHelper configHelper;

    @Override
    public RemoteTypeEnum getCode() {
        return RemoteTypeEnum.QW_ACCESS_TOKEN;
    }

    @Override
    public void buildRequest(Context context) {
        QwAppEnum appEnum = (QwAppEnum) context.getQueryDTO();
        RequestDTO requestDTO = new RequestDTO();
        ConfigDO config = configHelper.getConfigByGroupAndCode(CtConfigGroupEnum.QW_APP.name(), appEnum.getCode());
        Map<String, String> urlMap = JSONObject.parseObject(config.getValue(), Map.class);
        requestDTO.setUrlParam(urlMap);
        requestDTO.setUrl(url);
        context.setRequestDTO(requestDTO);
    }

    @Override
    public void enterCache(CacheableResultDTO resultDTO) {
        if (resultDTO.getEntity() != null) {
            QwAccessTokenRespDTO respDTO = (QwAccessTokenRespDTO) resultDTO.getEntity();
            if (StringUtils.equals(Constant.STRING_ZERO,respDTO.getErrCode())) {
                resultDTO.setEnterCache(Boolean.TRUE);
                resultDTO.setExpire(7200L);
            }
        }
    }

    @Override
    public QwAccessTokenRespDTO buildResponse(String resp) {
        if (StringUtils.isNotBlank(resp)) {
            QwAccessTokenRespDTO respDTO = JSON.parseObject(resp, QwAccessTokenRespDTO.class);
            return respDTO;
        }
        return null;
    }
}
