package com.openwjk.central.remote.service.impl.comwcapp.handle;

import com.openwjk.central.remote.dto.Context;
import com.openwjk.central.remote.dto.request.RequestDTO;
import com.openwjk.central.remote.enums.RemoteTypeEnum;
import com.openwjk.central.remote.service.IRemoteService;
import com.openwjk.commons.utils.HttpClientUtil;
import org.apache.http.entity.ContentType;
import org.springframework.stereotype.Service;

/**
 * @author wangjunkai
 * @description
 * @date 2023/7/30 15:14
 */
@Service
public class AppSendTextMsgRemoteHandler implements IRemoteService {
    @Override
    public RemoteTypeEnum getCode() {
        return RemoteTypeEnum.COM_WECHAT_APP_NOTICE_TEXT_MSG;
    }

    @Override
    public String callRemote(Context context) {
        RequestDTO requestDTO = context.getRequestDTO();
        String response = HttpClientUtil.httpPost(requestDTO.getUrl(), requestDTO.getBodyParam(), ContentType.APPLICATION_JSON);
        return response;
    }
}
