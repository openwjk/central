package com.openwjk.central.service.task;

import com.openwjk.central.commons.enums.CtConfigGroupEnum;
import com.openwjk.central.commons.enums.ScheduledTaskEnum;
import com.openwjk.central.commons.utils.RedisLockUtil;
import com.openwjk.central.dao.model.CtConfigDO;
import com.openwjk.central.remote.helper.ConfigHelper;
import com.openwjk.central.service.factory.ScheduledFactory;
import com.openwjk.central.service.helper.ScheduledHelper;
import com.openwjk.central.service.service.ScheduledService;
import com.openwjk.commons.utils.Constant;
import com.openwjk.commons.utils.DateUtil;
import com.openwjk.commons.utils.RandomCodeUtil;
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
    @Autowired
    RedisLockUtil redisLockUtil;

    @Scheduled(cron = "0/10 * * * * ?")
    public void runTask() {
        Date date = DateUtil.getNow();
        String key = CtConfigGroupEnum.SCHEDULED_TASK.getCode() + DateUtil.formatDate(date, DateUtil.FORMAT_DATE_NORMAL);
        String value = RandomCodeUtil.generateCode(16);
        try {
            if (redisLockUtil.tryLock(key, value, Constant.INT_TEN)) {
                List<CtConfigDO> configDOS = configHelper.getConfigByGroup(CtConfigGroupEnum.SCHEDULED_TASK.getCode());
                if (CollectionUtils.isEmpty(configDOS)) return;
                for (CtConfigDO configDO : configDOS) {
                    if (scheduledHelper.checkIsRun(configDO.getValue(), date)) {
                        ScheduledService scheduledService = scheduledFactory.getScheduledService(ScheduledTaskEnum.get(configDO.getCode()));
                        scheduledService.execute(date);
                    }
                }
            }
        } finally {
            redisLockUtil.releaseLock(key, value);
        }


    }
}
