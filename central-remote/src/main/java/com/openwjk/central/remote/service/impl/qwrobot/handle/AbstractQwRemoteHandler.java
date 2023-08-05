package com.openwjk.central.remote.service.impl.qwrobot.handle;

import com.openwjk.central.remote.dto.Context;
import com.openwjk.central.remote.service.IRemoteService;
import com.openwjk.commons.utils.HttpClientUtil;

import java.nio.charset.StandardCharsets;

/**
 * @author wangjunkai
 * @description
 * @date 2023/7/30 15:38
 */
public abstract class AbstractQwRemoteHandler implements IRemoteService {
    @Override
    public String callRemote(Context context) {
        String response = HttpClientUtil.httpPost(context.getRequestDTO().getUrl(), context.getRequestDTO().getBodyParam(), StandardCharsets.UTF_8.name());
        return response;
    }
}
