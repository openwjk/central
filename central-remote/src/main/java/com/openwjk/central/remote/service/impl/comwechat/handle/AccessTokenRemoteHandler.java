package com.openwjk.central.remote.service.impl.comwechat.handle;

import com.openwjk.central.remote.dto.Context;
import com.openwjk.central.remote.enums.RemoteTypeEnum;
import com.openwjk.commons.utils.HttpClientUtil;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

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

    @Override
    public String callRemote(Context context) {
        String response = HttpClientUtil.httpGet(context.getRequestDTO().getUrl(), null, context.getRequestDTO().getUrlParam());
        return response;
    }
}
