package com.openwjk.central.remote.service;

import com.openwjk.central.remote.dto.Context;
import com.openwjk.central.remote.enums.RemoteTypeEnum;

import java.io.Serializable;

/**
 * @author wangjunkai
 * @description
 * @date 2023/7/30 9:23
 */
public interface IDataService {
    RemoteTypeEnum getCode();

    void buildRequest(Context context);

    <RESP extends Serializable> RESP buildResponse(String resp);
}
