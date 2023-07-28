package com.openwjk.central.service.service;

import com.openwjk.central.service.enums.ScheduledEnum;

import java.util.Date;

/**
 * @author wangjunkai
 * @description
 * @date 2023/7/28 13:15
 */
public interface ScheduledService {
    ScheduledEnum getCode();

    void execute(Date date);
}
