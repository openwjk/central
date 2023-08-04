package com.openwjk.central.remote.service.impl.comwcapp;

import com.openwjk.central.commons.domain.CacheableResultDTO;
import com.openwjk.central.commons.domain.CommonQueryReqDTO;
import com.openwjk.central.commons.enums.ComWechatAppEnum;
import com.openwjk.central.commons.utils.CacheableUtil;
import com.openwjk.central.remote.dto.request.ComWcAppSendTextMsgReqDTO;
import com.openwjk.central.remote.dto.request.ComWechatRobotReqDTO;
import com.openwjk.central.remote.dto.response.ComWcAppSendTextMsgRespDTO;
import com.openwjk.central.remote.dto.response.ComWechatAccessTokenRespDTO;
import com.openwjk.central.remote.dto.response.CommonQueryRespDTO;
import com.openwjk.central.remote.enums.RemoteTypeEnum;
import com.openwjk.central.remote.enums.RespTypeEnum;
import com.openwjk.central.remote.service.ComWcAppService;
import com.openwjk.central.remote.service.ComWcRobotService;
import com.openwjk.central.remote.service.CommonCacheQueryService;
import com.openwjk.commons.exception.CommonsException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangjunkai
 * @description 企业微信应用
 * @date 2023/7/30 9:13
 */
@Service("comWcAppService")
public class ComWcAppServiceImpl implements ComWcAppService {
    private static final String SIMPLE_CACHE = "simpleCache";

    @Autowired
    CommonCacheQueryService queryService;

    /**
     * 获取应用token
     *
     * @param appEnum
     * @return
     */
    @Override
    public CommonQueryRespDTO<ComWechatAccessTokenRespDTO> getAppAccessToken(ComWechatAppEnum appEnum) {
        if (appEnum == null) {
            throw new CommonsException("param is invalid");
        }
        CommonQueryReqDTO reqDTO = new CommonQueryReqDTO(appEnum, SIMPLE_CACHE, CacheableUtil.buildCacheKey(appEnum.name()), RemoteTypeEnum.COM_WECHAT_ACCESS_TOKEN.name());
        CacheableResultDTO<ComWechatAccessTokenRespDTO> resultDTO = queryService.query(reqDTO);
        if (resultDTO.getEntity() != null && StringUtils.isNotBlank(resultDTO.getEntity().getAccessToken())) {
            return new CommonQueryRespDTO<>(RespTypeEnum.SUCCESS, resultDTO.getEntity());
        } else {
            return new CommonQueryRespDTO<>(RespTypeEnum.FAIL);
        }
    }

    /**
     * app发送文本消息
     *
     * @param textMsgReqDTO
     * @return
     */
    @Override
    public CommonQueryRespDTO<ComWcAppSendTextMsgRespDTO> appSendTextMsg(ComWcAppSendTextMsgReqDTO textMsgReqDTO) {
        if (textMsgReqDTO == null) {
            throw new CommonsException("param is invalid");
        }
        CommonQueryRespDTO<ComWechatAccessTokenRespDTO> respDTO = getAppAccessToken(textMsgReqDTO.getComWechatAppEnum());
        if (!RespTypeEnum.SUCCESS.equals(respDTO.getRespType()) || StringUtils.isBlank(respDTO.getRespEntry().getAccessToken())) {
            throw new CommonsException("getAppAccessToken failed");
        }
        textMsgReqDTO.setToken(respDTO.getRespEntry().getAccessToken());
        CommonQueryReqDTO reqDTO = new CommonQueryReqDTO(textMsgReqDTO, SIMPLE_CACHE, RemoteTypeEnum.COM_WECHAT_APP_NOTICE_TEXT_MSG.name(), false);
        CacheableResultDTO<ComWcAppSendTextMsgRespDTO> resultDTO = queryService.query(reqDTO);
        if (resultDTO.getEntity() != null) {
            return new CommonQueryRespDTO<>(RespTypeEnum.SUCCESS, resultDTO.getEntity());
        } else {
            return new CommonQueryRespDTO<>(RespTypeEnum.FAIL);
        }
    }
}
