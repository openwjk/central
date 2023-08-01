package com.openwjk.central.commons.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wangjunkai
 * @description
 * @date 2023/7/30 9:58
 */
@Data
public class CommonQueryReqDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String remoteType;
    private Object queryDTO;
    private String cacheService;
    private String cacheKey;
    private Boolean enterCache;


    public CommonQueryReqDTO(Object queryDTO, String cacheKey, String remoteType) {
        this.queryDTO = queryDTO;
        this.cacheKey = cacheKey;
        this.remoteType = remoteType;
        this.enterCache = true;
    }

}
