package com.openwjk.central.remote.service.impl.wxmini;

import com.openwjk.central.commons.domain.CacheableResultDTO;
import com.openwjk.central.commons.domain.CommonQueryReqDTO;
import com.openwjk.central.remote.dto.response.CommonQueryRespDTO;
import com.openwjk.central.remote.dto.response.WxminiSessionRespDTO;
import com.openwjk.central.remote.enums.RemoteTypeEnum;
import com.openwjk.central.remote.enums.RespTypeEnum;
import com.openwjk.central.remote.service.CommonCacheQueryService;
import com.openwjk.central.remote.service.WxminiService;
import com.openwjk.commons.exception.CommonsException;
import com.openwjk.commons.utils.Constant;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author junkai.wang
 * @date 2025/4/23 20:23
 * @description
 */
@Service
@RequiredArgsConstructor
public class WxminiServiceImpl implements WxminiService {
    private static final String SIMPLE_CACHE = "simpleCache";
    private final CommonCacheQueryService queryService;

    @Override
    public CommonQueryRespDTO<WxminiSessionRespDTO> getSession(String jsCode) {
        if (StringUtils.isBlank(jsCode)) {
            throw new CommonsException("param is invalid");
        }
        CommonQueryReqDTO reqDTO = new CommonQueryReqDTO(jsCode, SIMPLE_CACHE, Constant.EMPTY_STR, RemoteTypeEnum.WX_MINI_SESSION.name());
        CacheableResultDTO<WxminiSessionRespDTO> resultDTO = queryService.query(reqDTO);
        if (resultDTO.getEntity() != null) {
            return new CommonQueryRespDTO<>(RespTypeEnum.SUCCESS, resultDTO.getEntity());
        } else {
            return new CommonQueryRespDTO<>(RespTypeEnum.FAIL);
        }
    }
}
