package com.openwjk.central.remote.service;

import com.openwjk.central.remote.dto.request.QwRobotReqDTO;
import com.openwjk.central.remote.dto.response.CommonQueryRespDTO;
import com.openwjk.central.remote.dto.response.WxminiSessionRespDTO;

/**
 * @author wangjunkai
 * @description 微信小程序
 * @date 2025/3/30 8:25
 */
public interface WxminiService {
    CommonQueryRespDTO<WxminiSessionRespDTO> getSession(String jsCode);

}
