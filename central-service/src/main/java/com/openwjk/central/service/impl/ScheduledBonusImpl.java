package com.openwjk.central.service.impl;

import com.alibaba.fastjson2.JSON;
import com.google.common.collect.Lists;
import com.openwjk.central.commons.enums.QwRobotEnum;
import com.openwjk.central.commons.enums.ScheduledTaskEnum;
import com.openwjk.central.dao.model.ConfigDO;
import com.openwjk.central.remote.dto.request.QwRobotReqDTO;
import com.openwjk.central.remote.helper.ConfigHelper;
import com.openwjk.central.remote.service.QwAppService;
import com.openwjk.central.remote.service.QwRobotService;
import com.openwjk.central.service.domain.ScheduleNoticeDomain;
import com.openwjk.central.service.helper.ScheduledHelper;
import com.openwjk.central.service.service.ScheduledService;
import com.openwjk.commons.utils.Constant;
import com.openwjk.commons.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wangjunkai
 * @description
 * @date 2023/7/28 13:38
 */
@Service
@Slf4j
public class ScheduledBonusImpl implements ScheduledService {
    @Autowired
    ConfigHelper configHelper;
    @Autowired
    ScheduledHelper scheduledHelper;
    @Autowired
    QwAppService qwAppService;
    @Autowired
    @Qualifier("qwRobotService")
    QwRobotService qwRobotService;
    @Value("${qw.sendAppMsg.notice.agentId}")
    private String agentId;

    @Override
    public ScheduledTaskEnum getCode() {
        return ScheduledTaskEnum.BONUS;
    }

    @Override
    public void execute(Date date) {
        log.info(DateUtil.formatDate(date, DateUtil.FORMAT_DATETIME_NORMAL));
        Date tdate = DateUtil.plusMinutes(date, Constant.INT_THREE);
        List<ConfigDO> configDOS = configHelper.getConfigByGroup(ScheduledTaskEnum.BONUS.getCode());
        if (CollectionUtils.isEmpty(configDOS)) return;
        List<String> verbalTrickList = Lists.newArrayList();
        int sort = Constant.INT_ZERO;
        for (int i = 0; i < configDOS.size(); i++) {
            ScheduleNoticeDomain domain = JSON.parseObject(configDOS.get(i).getValue(), ScheduleNoticeDomain.class);
            if (scheduledHelper.checkIsRun(domain.getArgs().get(Constant.INT_ZERO), tdate)) {
                verbalTrickList.add((++sort) + Constant.POINT + domain.getVerbalTrick());
            }
        }
        if (CollectionUtils.isEmpty(verbalTrickList)) return;
        String verbalTrick = mergeVerbalTrick(verbalTrickList, tdate);
        log.info(verbalTrick);
        sendMsg(verbalTrick);
    }

    private String mergeVerbalTrick(List<String> verbalTrickList, Date date) {
        String time = DateUtil.formatDate(date, DateUtil.FORMAT_TIME_COMPACT_TILL_MINUTE);
        String verbalTrick = time + Constant.SPACE + ScheduledTaskEnum.BONUS.getDesc() + Constant.LINE_FEED;
        verbalTrick = verbalTrick + verbalTrickList.stream().collect(Collectors.joining(Constant.LINE_FEED));
        return verbalTrick;
    }


    private void sendMsg(String verbalTrick) {
//        QwAppSendTextMsgReqDTO reqDTO = new QwAppSendTextMsgReqDTO();
//        reqDTO.setQwAppEnum(QwAppEnum.NOTIFICATION);
//        reqDTO.setToUser("@all");
//        reqDTO.setMsgType(QwAppMsgTypeEnum.TEXT.getValue());
//        reqDTO.setAgentId(agentId);
//        QwAppSendTextMsgReqDTO.Content text = new QwAppSendTextMsgReqDTO.Content();
//        text.setContent(verbalTrick);
//        reqDTO.setText(text);
//        reqDTO.setSafe(Constant.STRING_ONE);
//        qwAppService.appSendTextMsg(reqDTO);
        if (StringUtils.isNotBlank(verbalTrick)) {
            QwRobotReqDTO reqDTO = new QwRobotReqDTO();
            reqDTO.setRobotEnum(QwRobotEnum.XXW);
            reqDTO.setVerbalTrick(verbalTrick);
            qwRobotService.sendTextRobot(reqDTO);
        }
    }

}
