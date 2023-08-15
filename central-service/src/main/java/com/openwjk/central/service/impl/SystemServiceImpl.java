package com.openwjk.central.service.impl;

import com.openwjk.central.commons.domain.CacheableResultDTO;
import com.openwjk.central.commons.domain.CommonQueryReqDTO;
import com.openwjk.central.remote.dto.request.QwRobotReqDTO;
import com.openwjk.central.service.service.SystemService;
import com.openwjk.commons.enums.ResponseEnum;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class SystemServiceImpl implements SystemService {
    @Override
    public String checkRun() {
        return ResponseEnum.SUCCESS.getMsg();
    }

}
