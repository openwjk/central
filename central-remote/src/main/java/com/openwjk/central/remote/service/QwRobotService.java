package com.openwjk.central.remote.service;

import com.openwjk.central.remote.dto.request.QwRobotReqDTO;
import com.openwjk.central.remote.dto.response.CommonQueryRespDTO;

/**
 * @author wangjunkai
 * @description 企业微信调用接口
 * @date 2023/7/30 8:25
 */
public interface QwRobotService {
    CommonQueryRespDTO<Boolean> sendTextRobot(QwRobotReqDTO robot);

    CommonQueryRespDTO<Boolean> sendMarkDownRobot(QwRobotReqDTO robot);

}
