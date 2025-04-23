package com.openwjk.central.service.impl;

import com.openwjk.central.remote.dto.response.CommonQueryRespDTO;
import com.openwjk.central.remote.dto.response.WxminiSessionRespDTO;
import com.openwjk.central.remote.service.WxminiService;
import com.openwjk.central.service.service.WxminiProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author junkai.wang
 * @date 2025/4/23 22:11
 * @description TODO
 */
@Service
@RequiredArgsConstructor
public class WxminiProgramServiceImpl implements WxminiProgramService {
    private final WxminiService wxminiService;

    @Override
    public WxminiSessionRespDTO getSession(String jsCode) {
        CommonQueryRespDTO<WxminiSessionRespDTO> commonQueryRespDTO = wxminiService.getSession(jsCode);
        return commonQueryRespDTO.getRespEntry();
    }


}
