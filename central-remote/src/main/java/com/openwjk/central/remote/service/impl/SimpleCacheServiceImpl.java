package com.openwjk.central.remote.service.impl;

import com.openwjk.central.commons.domain.CacheableResultDTO;
import com.openwjk.central.commons.domain.CommonQueryReqDTO;
import com.openwjk.central.commons.service.ICacheService;
import com.openwjk.central.remote.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

import java.io.Serializable;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/1 15:34
 */
public class SimpleCacheServiceImpl implements ICacheService {
    private static final String CODE = "simpleCache";
    private static final Long DEFAULT_CACHE_EXPIRE = 86400L;
    @Autowired
    private QueryService queryService;

    protected <RESP extends Serializable> CacheableResultDTO<RESP> before(CommonQueryReqDTO queryDTO) {
        return null;
    }

    @Override
    public String getCode() {
        return CODE;
    }

    @Override
    @Cacheable(cacheNames = "default", key = "#queryDTO.cacheKey")
    public <RESP extends Serializable> CacheableResultDTO<RESP> process(CommonQueryReqDTO queryDTO) {
        CacheableResultDTO<RESP> resultDTO = before(queryDTO);
        if (resultDTO == null) {
            resultDTO = queryService.query(queryDTO);
        }
        resultDTO = after(queryDTO, resultDTO);
        return resultDTO;
    }

    protected <RESP extends Serializable> CacheableResultDTO<RESP> after(CommonQueryReqDTO queryDTO, CacheableResultDTO<RESP> resultDTO) {
        if (!queryDTO.getEnterCache()) {
            resultDTO.setEnterCache(Boolean.FALSE);
        }
        if (resultDTO != null && resultDTO.getExpire() == null) {
            resultDTO.setExpire(DEFAULT_CACHE_EXPIRE);
        }
        return resultDTO;
    }
}
