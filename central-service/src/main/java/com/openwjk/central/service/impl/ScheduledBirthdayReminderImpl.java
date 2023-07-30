package com.openwjk.central.service.impl;

import com.alibaba.fastjson2.JSON;
import com.openwjk.central.commons.enums.ComWechatRobotEnum;
import com.openwjk.central.commons.enums.ScheduledTaskEnum;
import com.openwjk.central.dao.model.CtConfigDO;
import com.openwjk.central.remote.dto.request.ComWechatRobotReqDTO;
import com.openwjk.central.remote.helper.ConfigHelper;
import com.openwjk.central.remote.service.impl.comwechat.ComWechatServiceImpl;
import com.openwjk.central.service.domain.BirthDayDomain;
import com.openwjk.central.service.service.ScheduledService;
import com.openwjk.commons.utils.ChineseCalendar;
import com.openwjk.commons.utils.DateUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    ComWechatServiceImpl comWechatService;

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
        CtConfigDO configDO = configHelper.getConfigByGroupAndCode(getCode().name(), birthDay);
        if (configDO == null) return;
        sendMsg(LUNAR + birthDay, configDO);
    }

    private void sendSolarCalendarBirthDay(Date date) {
        String birthDay = DateUtil.formatDate(date, FORMAT_DATE_CN_MONTH_DAY);
        CtConfigDO configDO = configHelper.getConfigByGroupAndCode(getCode().name(), birthDay);
        if (configDO == null) return;
        sendMsg(birthDay, configDO);
    }

    private void sendMsg(String birthDay, CtConfigDO configDO) {
        BirthDayDomain birthDayDomain = JSON.parseObject(configDO.getValue(), BirthDayDomain.class);
        if (birthDayDomain == null || CollectionUtils.isEmpty(birthDayDomain.getArgs())) return;
        String names = birthDayDomain.getArgs().stream().collect(Collectors.joining(","));
        String verbalTrick = String.format(DEFAULT_VERBAL_TRICK, birthDay, names);
        if (StringUtils.isNotBlank(birthDayDomain.getVerbalTrick())) {
            verbalTrick = birthDayDomain.getVerbalTrick();
            for (String arg : birthDayDomain.getArgs()) {
                verbalTrick = verbalTrick.replace("%s", arg);
            }
        }
        comWechatService.sendTextRobot(new ComWechatRobotReqDTO(verbalTrick, ComWechatRobotEnum.WLCJDIYS));
    }

}
