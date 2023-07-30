package com.openwjk.central.remote.service;

import com.openwjk.central.remote.dto.Context;
import com.openwjk.central.remote.dto.request.CommonQueryReqDTO;
import com.openwjk.central.remote.dto.response.CommonQueryRespDTO;
import com.openwjk.central.remote.enums.RespTypeEnum;
import com.openwjk.central.remote.factory.RemoteFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author wangjunkai
 * @description
 * @date 2023/7/30 10:08
 */
@Service
public class QueryService {
    @Autowired
    RemoteFactory remoteFactory;
    @Cacheable(cacheNames = "default",key = "#queryReqDTO.cacheKey")
    public CommonQueryRespDTO query(CommonQueryReqDTO queryReqDTO) {
        CommonQueryRespDTO respDTO = new CommonQueryRespDTO();
        try {
            Context context = new Context();
            IDataService dataService = remoteFactory.getDataService(queryReqDTO.getRemoteTypeEnum());
            IRemoteService remoteService = remoteFactory.getRemoteService(queryReqDTO.getRemoteTypeEnum());
            dataService.buildRequest(queryReqDTO.getQueryDTO(),context);
            String response = remoteService.callRemote(context);
            respDTO.setRespEntry(dataService.buildResponse(response));
        } catch (Exception e) {
            respDTO.setRespType(RespTypeEnum.FAIL);
        }
        return respDTO;
    }
}
