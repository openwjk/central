package com.openwjk.central.remote.service;

import com.openwjk.central.commons.enums.ComWechatAppEnum;
import com.openwjk.central.remote.dto.request.ComWechatRobotReqDTO;
import com.openwjk.central.remote.dto.response.CommonQueryRespDTO;

/**
 * @author wangjunkai
 * @description 企业微信调用接口
 * @date 2023/7/30 8:25
 */
public interface ComWechatService {
    CommonQueryRespDTO<Boolean> sendTextRobot(ComWechatRobotReqDTO robot);

    CommonQueryRespDTO<Boolean> sendMarkDownRobot(ComWechatRobotReqDTO robot);

    CommonQueryRespDTO<Boolean> getAppAccessToken(ComWechatAppEnum appEnum);
}
