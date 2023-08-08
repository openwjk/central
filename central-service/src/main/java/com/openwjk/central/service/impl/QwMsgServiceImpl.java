package com.openwjk.central.service.impl;

import com.openwjk.central.commons.enums.QwAppEnum;
import com.openwjk.central.commons.enums.QwAppMsgTypeEnum;
import com.openwjk.central.remote.dto.request.QwAppSendTextMsgReqDTO;
import com.openwjk.central.remote.service.QwAppService;
import com.openwjk.central.service.domain.req.WebhookReqVO;
import com.openwjk.central.service.service.QwMsgService;
import com.openwjk.commons.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/5 10:46
 */
@Service
public class QwMsgServiceImpl implements QwMsgService {
    @Autowired
    QwAppService qwAppService;
    @Value("${qw.sendAppMsg.notice.textToUser}")
    private String toUser;
    @Value("${qw.sendAppMsg.notice.agentId}")
    private String agentId;
    @Override
    public void sendDdnsMsg(WebhookReqVO reqVO) {
        QwAppSendTextMsgReqDTO reqDTO = new QwAppSendTextMsgReqDTO();
        reqDTO.setQwAppEnum(QwAppEnum.NOTIFICATION);
        reqDTO.setToUser(toUser);
        reqDTO.setMsgType(QwAppMsgTypeEnum.TEXT.getValue());
        reqDTO.setAgentId(agentId);
        QwAppSendTextMsgReqDTO.Content text = new QwAppSendTextMsgReqDTO.Content();
        text.setContent(reqVO.getText().getContent());
        reqDTO.setText(text);
        reqDTO.setSafe(Constant.STRING_ONE);
        qwAppService.appSendTextMsg(reqDTO);
    }
}
