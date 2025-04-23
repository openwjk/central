package com.openwjk.central.remote.service.impl.wxmini.handle;

import com.openwjk.central.remote.dto.Context;
import com.openwjk.central.remote.enums.RemoteTypeEnum;
import com.openwjk.central.remote.service.IRemoteService;
import com.openwjk.commons.utils.HttpClientUtil;
import org.springframework.stereotype.Service;

/**
 * @author wangjunkai
 * @description
 * @date 2023/7/30 15:14
 */
@Service
public class WxminiSerssionRemoteHandler implements IRemoteService {
    @Override
    public RemoteTypeEnum getCode() {
        return RemoteTypeEnum.WX_MINI_SESSION;
    }

    @Override
    public String callRemote(Context context) {
        return HttpClientUtil.httpGet(context.getRequestDTO().getUrl(), null, context.getRequestDTO().getUrlParam());
    }
}
