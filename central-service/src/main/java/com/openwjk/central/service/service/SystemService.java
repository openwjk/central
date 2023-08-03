package com.openwjk.central.service.service;

import com.openwjk.central.commons.domain.CacheableResultDTO;
import com.openwjk.central.commons.domain.CommonQueryReqDTO;

public interface SystemService {
    String checkRun();

    CacheableResultDTO redisTest(CommonQueryReqDTO reqDTO);
}
