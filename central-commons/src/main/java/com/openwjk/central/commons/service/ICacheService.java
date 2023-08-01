package com.openwjk.central.commons.service;

import com.openwjk.central.commons.domain.CacheableResultDTO;
import com.openwjk.central.commons.domain.CommonQueryReqDTO;

import java.io.Serializable;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/1 15:10
 */
public interface ICacheService {
    String getCode();

    <RESP extends Serializable> CacheableResultDTO<RESP> process(CommonQueryReqDTO queryDTO);
}
