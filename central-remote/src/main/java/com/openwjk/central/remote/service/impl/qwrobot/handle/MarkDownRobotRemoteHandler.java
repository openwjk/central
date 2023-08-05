package com.openwjk.central.remote.service.impl.qwrobot.handle;

import com.openwjk.central.remote.enums.RemoteTypeEnum;
import org.springframework.stereotype.Service;

/**
 * @author wangjunkai
 * @description
 * @date 2023/7/30 15:14
 */
@Service
public class MarkDownRobotRemoteHandler extends AbstractQwRemoteHandler {
    @Override
    public RemoteTypeEnum getCode() {
        return RemoteTypeEnum.QW_MARK_DOWN_ROBOT;
    }

}
