package com.openwjk.central.remote.service.impl.comwechat.handle;

import com.openwjk.central.remote.enums.RemoteTypeEnum;
import org.springframework.stereotype.Service;

/**
 * @author wangjunkai
 * @description
 * @date 2023/7/30 15:14
 */
@Service
public class AccessTokenRemoteHandler extends AbstractComWechatRemoteHandler {
    @Override
    public RemoteTypeEnum getCode() {
        return RemoteTypeEnum.COM_WECHAT_ACCESS_TOKEN;
    }
}
