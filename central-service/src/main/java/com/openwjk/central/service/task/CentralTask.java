package com.openwjk.central.service.task;

import com.openwjk.central.dao.model.CtConfigDO;
import com.openwjk.central.service.enums.CtConfigGroupEnum;
import com.openwjk.central.service.enums.ScheduledTaskEnum;
import com.openwjk.central.service.factory.ScheduledFactory;
import com.openwjk.central.service.helper.ConfigHelper;
import com.openwjk.central.service.helper.ScheduledHelper;
import com.openwjk.central.service.service.ScheduledService;
import com.openwjk.commons.utils.DateUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CentralTask {
    @Autowired
    ConfigHelper configHelper;
    @Autowired
    ScheduledHelper scheduledHelper;
    @Autowired
    ScheduledFactory scheduledFactory;

    @Scheduled(cron = "0/1 * * * * ?")
    public void runTask() {
        Date date = DateUtil.getNow();
        List<CtConfigDO> configDOS = configHelper.getConfigByGroup(CtConfigGroupEnum.SCHEDULED_TASK.getCode());
        if (CollectionUtils.isEmpty(configDOS)) return;
        for (CtConfigDO configDO : configDOS) {
            if (scheduledHelper.checkIsRun(configDO.getValue(), date)) {
                ScheduledService scheduledService = scheduledFactory.getScheduledService(ScheduledTaskEnum.get(configDO.getCode()));
                scheduledService.execute(date);
            }
        }
    }
}
