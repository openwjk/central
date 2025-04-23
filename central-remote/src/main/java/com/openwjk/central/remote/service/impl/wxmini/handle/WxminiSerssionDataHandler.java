package com.openwjk.central.remote.service.impl.wxmini.handle;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.openwjk.central.commons.domain.CacheableResultDTO;
import com.openwjk.central.commons.enums.CtConfigGroupEnum;
import com.openwjk.central.commons.enums.QwAppEnum;
import com.openwjk.central.commons.enums.WxminiEnum;
import com.openwjk.central.dao.model.ConfigDO;
import com.openwjk.central.remote.dto.Context;
import com.openwjk.central.remote.dto.request.RequestDTO;
import com.openwjk.central.remote.dto.response.QwAccessTokenRespDTO;
import com.openwjk.central.remote.dto.response.WxminiSessionRespDTO;
import com.openwjk.central.remote.enums.RemoteTypeEnum;
import com.openwjk.central.remote.helper.ConfigHelper;
import com.openwjk.central.remote.service.IDataService;
import com.openwjk.commons.utils.Constant;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class WxminiSerssionDataHandler implements IDataService {

    @Value("${wxmini.session.url}")
    private String url;

    private final ConfigHelper configHelper;

    @Override
    public RemoteTypeEnum getCode() {
        return RemoteTypeEnum.WX_MINI_SESSION;
    }

    @Override
    public void buildRequest(Context context) {
        RequestDTO requestDTO = new RequestDTO();
        ConfigDO config = configHelper.getConfigByGroupAndCode(CtConfigGroupEnum.WX_MINI.name(), WxminiEnum.SESSION.getCode());
        Map<String, String> urlMap = JSONObject.parseObject(config.getValue(), Map.class);
        urlMap.put("js_code", (String) context.getQueryDTO());
        requestDTO.setUrlParam(urlMap);
        requestDTO.setUrl(url);
        context.setRequestDTO(requestDTO);
    }

    @Override
    public void enterCache(CacheableResultDTO resultDTO) {
        if (resultDTO.getEntity() != null) {
            WxminiSessionRespDTO respDTO = (WxminiSessionRespDTO) resultDTO.getEntity();
            if (StringUtils.equals(Constant.STRING_ZERO, respDTO.getErrcode())) {
                resultDTO.setEnterCache(Boolean.TRUE);
                resultDTO.setExpire(7200L);
            }
        }
    }

    @Override
    public WxminiSessionRespDTO buildResponse(String resp) {
        if (StringUtils.isNotBlank(resp))
            return JSON.parseObject(resp, WxminiSessionRespDTO.class);
        return null;
    }
}
