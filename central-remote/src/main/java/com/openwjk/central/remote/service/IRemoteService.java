package com.openwjk.central.remote.service;

import com.openwjk.central.remote.dto.Context;
import com.openwjk.central.remote.enums.RemoteTypeEnum;

/**
 * @author wangjunkai
 * @description
 * @date 2023/7/30 9:23
 */
public interface IRemoteService {
    RemoteTypeEnum getCode();

    String callRemote(Context context);
}
