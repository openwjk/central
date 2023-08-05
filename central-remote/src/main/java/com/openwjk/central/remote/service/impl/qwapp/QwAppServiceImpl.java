package com.openwjk.central.remote.service.impl.qwapp;

import com.openwjk.central.commons.domain.CacheableResultDTO;
import com.openwjk.central.commons.domain.CommonQueryReqDTO;
import com.openwjk.central.commons.enums.QwAppEnum;
import com.openwjk.central.commons.utils.CacheableUtil;
import com.openwjk.central.remote.dto.request.QwAppSendMarkdownReqDTO;
import com.openwjk.central.remote.dto.request.QwAppSendTextMsgReqDTO;
import com.openwjk.central.remote.dto.response.QwAppSendMsgRespDTO;
import com.openwjk.central.remote.dto.response.QwAccessTokenRespDTO;
import com.openwjk.central.remote.dto.response.CommonQueryRespDTO;
import com.openwjk.central.remote.enums.RemoteTypeEnum;
import com.openwjk.central.remote.enums.RespTypeEnum;
import com.openwjk.central.remote.service.QwAppService;
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
@Service("qwAppService")
public class QwAppServiceImpl implements QwAppService {
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
    public CommonQueryRespDTO<QwAccessTokenRespDTO> getAppAccessToken(QwAppEnum appEnum) {
        if (appEnum == null) {
            throw new CommonsException("param is invalid");
        }
        CommonQueryReqDTO reqDTO = new CommonQueryReqDTO(appEnum, SIMPLE_CACHE, CacheableUtil.buildCacheKey(
                RemoteTypeEnum.QW_ACCESS_TOKEN.name(), appEnum.name()), RemoteTypeEnum.QW_ACCESS_TOKEN.name());
        CacheableResultDTO<QwAccessTokenRespDTO> resultDTO = queryService.query(reqDTO);
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
    public CommonQueryRespDTO<QwAppSendMsgRespDTO> appSendTextMsg(QwAppSendTextMsgReqDTO textMsgReqDTO) {
        if (textMsgReqDTO == null) {
            throw new CommonsException("param is invalid");
        }
        CommonQueryRespDTO<QwAccessTokenRespDTO> respDTO = getAppAccessToken(textMsgReqDTO.getQwAppEnum());
        if (!RespTypeEnum.SUCCESS.equals(respDTO.getRespType()) || StringUtils.isBlank(respDTO.getRespEntry().getAccessToken())) {
            throw new CommonsException("getAppAccessToken failed");
        }
        textMsgReqDTO.setToken(respDTO.getRespEntry().getAccessToken());
        CommonQueryReqDTO reqDTO = new CommonQueryReqDTO(textMsgReqDTO, SIMPLE_CACHE,
                RemoteTypeEnum.QW_APP_NOTICE_TEXT_MSG.name());
        CacheableResultDTO<QwAppSendMsgRespDTO> resultDTO = queryService.query(reqDTO);
        if (resultDTO.getEntity() != null) {
            return new CommonQueryRespDTO<>(RespTypeEnum.SUCCESS, resultDTO.getEntity());
        } else {
            return new CommonQueryRespDTO<>(RespTypeEnum.FAIL);
        }
    }

    /**
     * app发送markdown
     *
     * @param markdownReqDTO
     * @return
     */
    @Override
    public CommonQueryRespDTO<QwAppSendMsgRespDTO> appSendMarkdown(QwAppSendMarkdownReqDTO markdownReqDTO) {
        if (markdownReqDTO == null) {
            throw new CommonsException("param is invalid");
        }
        CommonQueryRespDTO<QwAccessTokenRespDTO> respDTO = getAppAccessToken(markdownReqDTO.getQwAppEnum());
        if (!RespTypeEnum.SUCCESS.equals(respDTO.getRespType()) || StringUtils.isBlank(respDTO.getRespEntry().getAccessToken())) {
            throw new CommonsException("getAppAccessToken failed");
        }
        markdownReqDTO.setToken(respDTO.getRespEntry().getAccessToken());
        CommonQueryReqDTO reqDTO = new CommonQueryReqDTO(markdownReqDTO, SIMPLE_CACHE,
                RemoteTypeEnum.QW_APP_NOTICE_MARKDOWN_MSG.name());
        CacheableResultDTO<QwAppSendMsgRespDTO> resultDTO = queryService.query(reqDTO);
        if (resultDTO.getEntity() != null) {
            return new CommonQueryRespDTO<>(RespTypeEnum.SUCCESS, resultDTO.getEntity());
        } else {
            return new CommonQueryRespDTO<>(RespTypeEnum.FAIL);
        }
    }
}
