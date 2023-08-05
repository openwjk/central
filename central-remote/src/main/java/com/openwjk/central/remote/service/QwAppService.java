package com.openwjk.central.remote.service;

import com.openwjk.central.commons.enums.QwAppEnum;
import com.openwjk.central.remote.dto.request.QwAppSendMarkdownReqDTO;
import com.openwjk.central.remote.dto.request.QwAppSendTextMsgReqDTO;
import com.openwjk.central.remote.dto.response.QwAppSendMsgRespDTO;
import com.openwjk.central.remote.dto.response.QwAccessTokenRespDTO;
import com.openwjk.central.remote.dto.response.CommonQueryRespDTO;

/**
 * @author wangjunkai
 * @description 企业微信调用接口
 * @date 2023/7/30 8:25
 */
public interface QwAppService {
    CommonQueryRespDTO<QwAccessTokenRespDTO> getAppAccessToken(QwAppEnum appEnum);

    CommonQueryRespDTO<QwAppSendMsgRespDTO> appSendTextMsg(QwAppSendTextMsgReqDTO reqDTO);

    CommonQueryRespDTO<QwAppSendMsgRespDTO> appSendMarkdown(QwAppSendMarkdownReqDTO markdownReqDTO);
}
