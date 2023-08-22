package com.openwjk.central.service.impl;

import com.alibaba.fastjson2.JSON;
import com.openwjk.central.commons.enums.QwAppEnum;
import com.openwjk.central.commons.enums.QwAppMsgTypeEnum;
import com.openwjk.central.commons.enums.ScheduledTaskEnum;
import com.openwjk.central.dao.model.ConfigDO;
import com.openwjk.central.remote.dto.request.QwAppSendTextMsgReqDTO;
import com.openwjk.central.remote.helper.ConfigHelper;
import com.openwjk.central.remote.service.QwAppService;
import com.openwjk.central.remote.service.impl.qwrobot.QwRobotServiceImpl;
import com.openwjk.central.service.domain.ScheduleNoticeDomain;
import com.openwjk.central.service.service.ScheduledService;
import com.openwjk.commons.utils.ChineseCalendar;
import com.openwjk.commons.utils.Constant;
import com.openwjk.commons.utils.DateUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Collectors;

/**
 * @author wangjunkai
 * @description
 * @date 2023/7/28 13:38
 */
@Service
public class ScheduledBirthdayReminderImpl implements ScheduledService {
    public static final String FORMAT_DATE_CN_MONTH_DAY = "M月d号";
    public static final String LUNAR = "农历";
    public static final String DEFAULT_VERBAL_TRICK = "今天%s是%s生日，记得送上生日祝福哦~";

    @Autowired
    ConfigHelper configHelper;
    @Autowired
    QwAppService qwAppService;
    @Value("${qw.sendAppMsg.notice.agentId}")
    private String agentId;

    @Override
    public ScheduledTaskEnum getCode() {
        return ScheduledTaskEnum.BIRTHDAY_REMINDER;
    }

    @Override
    public void execute(Date date) {
        sendLunarCalendarBirthDay(date);
        sendSolarCalendarBirthDay(date);
    }


    private void sendLunarCalendarBirthDay(Date date) {
        ChineseCalendar calendar = ChineseCalendar.as(date);
        String birthDay = calendar.getChinaString().substring(calendar.getChinaString().indexOf("年") + 1);
        ConfigDO configDO = configHelper.getConfigByGroupAndCode(getCode().name(), birthDay);
        if (configDO == null) return;
        sendMsg(LUNAR + birthDay, configDO);
    }

    private void sendSolarCalendarBirthDay(Date date) {
        String birthDay = DateUtil.formatDate(date, FORMAT_DATE_CN_MONTH_DAY);
        ConfigDO configDO = configHelper.getConfigByGroupAndCode(getCode().name(), birthDay);
        if (configDO == null) return;
        sendMsg(birthDay, configDO);
    }

    private String buildVerbalTrick(String birthDay, ConfigDO configDO) {
        ScheduleNoticeDomain birthDayDomain = JSON.parseObject(configDO.getValue(), ScheduleNoticeDomain.class);
        if (birthDayDomain == null || CollectionUtils.isEmpty(birthDayDomain.getArgs())) return Constant.EMPTY_STR;
        String names = birthDayDomain.getArgs().stream().collect(Collectors.joining(","));
        String verbalTrick = String.format(DEFAULT_VERBAL_TRICK, birthDay, names);
        if (StringUtils.isNotBlank(birthDayDomain.getVerbalTrick())) {
            verbalTrick = birthDayDomain.getVerbalTrick();
            for (String arg : birthDayDomain.getArgs()) {
                verbalTrick = verbalTrick.replace("%s", arg);
            }
        }
        return verbalTrick;
    }

    private void sendMsg(String birthDay, ConfigDO configDO) {
        String verbalTrick = buildVerbalTrick(birthDay, configDO);
        if (StringUtils.isBlank(verbalTrick)) return;
        QwAppSendTextMsgReqDTO reqDTO = new QwAppSendTextMsgReqDTO();
        reqDTO.setQwAppEnum(QwAppEnum.NOTIFICATION);
        reqDTO.setToUser("@all");
        reqDTO.setMsgType(QwAppMsgTypeEnum.TEXT.getValue());
        reqDTO.setAgentId(agentId);
        QwAppSendTextMsgReqDTO.Content text = new QwAppSendTextMsgReqDTO.Content();
        text.setContent(verbalTrick);
        reqDTO.setText(text);
        reqDTO.setSafe(Constant.STRING_ONE);
        qwAppService.appSendTextMsg(reqDTO);
    }

}
