package com.openwjk.central.service.impl;

import com.openwjk.central.commons.enums.ScheduledTaskEnum;
import com.openwjk.central.service.service.ScheduledService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/23 21:53
 */
@Service
public class ScheduleFestivalReminderImpl implements ScheduledService {
    @Override
    public ScheduledTaskEnum getCode() {
        return ScheduledTaskEnum.FESTIVAL_OVERTIME;
    }

    @Override
    public void execute(Date date) {

    }
}
