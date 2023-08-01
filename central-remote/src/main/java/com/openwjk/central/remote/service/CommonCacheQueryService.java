package com.openwjk.central.remote.service;

import com.openwjk.central.commons.domain.CacheableResultDTO;
import com.openwjk.central.commons.domain.CommonQueryReqDTO;
import com.openwjk.central.commons.service.ICacheService;
import com.openwjk.commons.exception.CommonsException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/1 16:17
 */
@Service
public class CommonCacheQueryService {

    @Autowired
    private List<ICacheService> cacheServiceList;

    public <RESP extends Serializable> CacheableResultDTO<RESP> query(CommonQueryReqDTO queryReqDTO) {
        ICacheService cacheService = getCacheService(queryReqDTO.getCacheService());
        return cacheService.process(queryReqDTO);
    }

    private ICacheService getCacheService(String cacheServiceKey) {
        for (ICacheService cacheService : cacheServiceList) {
            if (StringUtils.equals(cacheServiceKey, cacheService.getCode())) {
                return cacheService;
            }
        }
        throw new CommonsException(String.format("call CommonCacheQueryService.getCacheService, cacheServiceKey:[%s] is invalid."
                , cacheServiceKey));
    }
}
