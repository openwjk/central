package com.openwjk.central.remote.service.impl.comwcapp.handle;

import com.openwjk.central.remote.dto.Context;
import com.openwjk.central.remote.enums.RemoteTypeEnum;
import com.openwjk.central.remote.service.IRemoteService;
import com.openwjk.central.remote.service.impl.comwcrobot.handle.AbstractComWechatRemoteHandler;
import com.openwjk.commons.utils.HttpClientUtil;
import org.springframework.stereotype.Service;

/**
 * @author wangjunkai
 * @description
 * @date 2023/7/30 15:14
 */
@Service
public class AccessTokenRemoteHandler implements IRemoteService {
    @Override
    public RemoteTypeEnum getCode() {
        return RemoteTypeEnum.COM_WECHAT_ACCESS_TOKEN;
    }

    @Override
    public String callRemote(Context context) {
        String response = HttpClientUtil.httpGet(context.getRequestDTO().getUrl(), null, context.getRequestDTO().getUrlParam());
        return response;
    }
}
