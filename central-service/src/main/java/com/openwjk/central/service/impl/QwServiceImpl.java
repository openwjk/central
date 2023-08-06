package com.openwjk.central.service.impl;

import com.openwjk.central.commons.utils.qw.AesException;
import com.openwjk.central.commons.utils.qw.WXBizJsonMsgCrypt;
import com.openwjk.central.service.service.QwService;
import com.openwjk.commons.utils.StackTraceUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/6 19:50
 */
@Service
@Log4j2
public class QwServiceImpl implements QwService {

    @Override
    public String verify(String msg_signature, String timestamp, String nonce, String echostr) {
        String result = null;
        try {
            WXBizJsonMsgCrypt wxcpt = new WXBizJsonMsgCrypt("TOKEN", "ENCODINGAES_KEY", "CORP_ID");
            result = wxcpt.VerifyURL(msg_signature, timestamp, nonce, echostr);
        } catch (AesException e) {
            log.info(StackTraceUtil.getStackTrace(e));
        }
        if (result == null) {
            result = "TOKEN";
        }
        return result;
    }
}
