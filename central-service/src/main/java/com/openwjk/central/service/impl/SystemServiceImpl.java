package com.openwjk.central.service.impl;

import com.openwjk.central.commons.domain.CacheableResultDTO;
import com.openwjk.central.commons.domain.CommonQueryReqDTO;
import com.openwjk.central.remote.dto.request.ComWechatRobotReqDTO;
import com.openwjk.central.service.service.SystemService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class SystemServiceImpl implements SystemService {
    @Override
    public String checkRun() {
        return "success.";
    }

    @Override
    @Cacheable(cacheNames = "default",key = "#reqDTO.cacheKey")
    public CacheableResultDTO redisTest(CommonQueryReqDTO reqDTO) {
        CacheableResultDTO resultDTO = new CacheableResultDTO();
        ComWechatRobotReqDTO reqDTO1 = new ComWechatRobotReqDTO();
        reqDTO1.setVerbalTrick("1111");
        resultDTO.setEntity(reqDTO1);
        resultDTO.setEnterCache(true);
        resultDTO.setExpire(3600L);
        return resultDTO;
    }

}
