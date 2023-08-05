package com.openwjk.central.remote.service;

import com.openwjk.central.commons.domain.CacheableResultDTO;
import com.openwjk.central.remote.dto.Context;
import com.openwjk.central.commons.domain.CommonQueryReqDTO;
import com.openwjk.central.remote.enums.RemoteTypeEnum;
import com.openwjk.central.remote.factory.RemoteFactory;
import com.openwjk.commons.utils.StackTraceUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @author wangjunkai
 * @description
 * @date 2023/7/30 10:08
 */
@Service
@Log4j2
public class CommonQueryService {
    @Autowired
    RemoteFactory remoteFactory;

    public <RESPONSE extends Serializable> CacheableResultDTO<RESPONSE> query(CommonQueryReqDTO queryReqDTO) {
        CacheableResultDTO respDTO = new CacheableResultDTO();
        try {
            Context context = new Context(queryReqDTO.getQueryDTO());
            IDataService dataService = remoteFactory.getDataService(RemoteTypeEnum.get(queryReqDTO.getRemoteType()));
            IRemoteService remoteService = remoteFactory.getRemoteService(RemoteTypeEnum.get(queryReqDTO.getRemoteType()));
            dataService.buildRequest(context);
            String response = remoteService.callRemote(context);
            respDTO.setEntity(dataService.buildResponse(response));
            dataService.enterCache(respDTO);
        } catch (Exception e) {
            log.error(StackTraceUtil.getStackTrace(e));
        }
        return respDTO;
    }
}
