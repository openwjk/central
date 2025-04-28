package com.openwjk.central.service.service;

import com.openwjk.central.remote.dto.response.WxminiSessionRespDTO;
import com.openwjk.central.service.domain.resp.WxminiPhotoVO;

import java.util.List;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/7 10:50
 */
public interface WxminiProgramService {
    /**
     * 获取session
     * @param jsCode
     * @return
     */
    WxminiSessionRespDTO getSession(String jsCode);

    List<WxminiPhotoVO> getPhotos(String groupCode);
}
