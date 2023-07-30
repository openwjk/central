package com.openwjk.central.remote.service.impl.comwechat.handle;

import com.openwjk.central.remote.dto.Context;
import com.openwjk.central.remote.enums.RemoteTypeEnum;
import com.openwjk.central.remote.service.IDataService;
import org.springframework.stereotype.Service;

/**
 * @author wangjunkai
 * @description
 * @date 2023/7/30 11:01
 */
@Service
public class MarkDownRobotDataHandler implements IDataService {
    @Override
    public RemoteTypeEnum getCode() {
        return RemoteTypeEnum.COM_WECHAT_MARK_DOWN_ROBOT;
    }

    @Override
    public Context buildRequest(Object o) {
        return null;
    }

    @Override
    public Object buildResponse(String resp) {
        return null;
    }
}
