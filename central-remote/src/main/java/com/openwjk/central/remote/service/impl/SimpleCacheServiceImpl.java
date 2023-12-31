package com.openwjk.central.remote.service.impl;

import com.openwjk.central.commons.domain.CacheableResultDTO;
import com.openwjk.central.commons.domain.CommonQueryReqDTO;
import com.openwjk.central.commons.service.ICacheService;
import com.openwjk.central.remote.service.CommonQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/1 15:34
 */
@Service
public class SimpleCacheServiceImpl implements ICacheService {
    private static final String CODE = "simpleCache";
    private static final Long DEFAULT_CACHE_EXPIRE = 86400L;
    @Autowired
    private CommonQueryService queryService;

    protected <RESP extends Serializable> CacheableResultDTO<RESP> before(CommonQueryReqDTO queryDTO) {
        return null;
    }

    @Override
    public String getCode() {
        return CODE;
    }

    @Override
    @Cacheable(cacheNames = "default", key = "#queryDTO.cacheKey",condition = "#queryDTO.cacheKey != null")
    public <RESP extends Serializable> CacheableResultDTO<RESP> process(CommonQueryReqDTO queryDTO) {
        CacheableResultDTO<RESP> resultDTO = before(queryDTO);
        if (resultDTO == null) {
            resultDTO = queryService.query(queryDTO);
        }
        resultDTO = after(queryDTO, resultDTO);
        return resultDTO;
    }

    protected <RESP extends Serializable> CacheableResultDTO<RESP> after(CommonQueryReqDTO queryDTO, CacheableResultDTO<RESP> resultDTO) {
        if (resultDTO != null && resultDTO.getEnterCache() && resultDTO.getExpire() == null) {
            resultDTO.setExpire(DEFAULT_CACHE_EXPIRE);
        }
        return resultDTO;
    }
}
