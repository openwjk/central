package com.openwjk.central.service.service;


import com.openwjk.central.commons.enums.ScheduledTaskEnum;

import java.util.Date;

/**
 * @author wangjunkai
 * @description
 * @date 2023/7/28 13:15
 */
public interface ScheduledService {
    ScheduledTaskEnum getCode();

    void execute(Date date);
}
