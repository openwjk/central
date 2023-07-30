package com.openwjk.central.remote.service;

import com.openwjk.central.remote.dto.Context;
import com.openwjk.central.remote.enums.RemoteTypeEnum;

/**
 * @author wangjunkai
 * @description
 * @date 2023/7/30 9:23
 */
public interface IDataService<REQ,RESP> {
    RemoteTypeEnum getCode();

    void buildRequest(REQ req,Context context);

    RESP buildResponse(String resp);
}
