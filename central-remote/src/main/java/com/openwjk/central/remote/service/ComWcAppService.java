package com.openwjk.central.remote.service;

import com.openwjk.central.commons.enums.ComWechatAppEnum;
import com.openwjk.central.remote.dto.request.ComWcAppSendTextMsgReqDTO;
import com.openwjk.central.remote.dto.request.ComWechatRobotReqDTO;
import com.openwjk.central.remote.dto.response.ComWcAppSendTextMsgRespDTO;
import com.openwjk.central.remote.dto.response.ComWechatAccessTokenRespDTO;
import com.openwjk.central.remote.dto.response.ComWechatRobotRespDTO;
import com.openwjk.central.remote.dto.response.CommonQueryRespDTO;

/**
 * @author wangjunkai
 * @description 企业微信调用接口
 * @date 2023/7/30 8:25
 */
public interface ComWcAppService {
    CommonQueryRespDTO<ComWechatAccessTokenRespDTO> getAppAccessToken(ComWechatAppEnum appEnum);

    CommonQueryRespDTO<ComWcAppSendTextMsgRespDTO> appSendTextMsg(ComWcAppSendTextMsgReqDTO reqDTO);
}
