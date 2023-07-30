package com.openwjk.central.service.factory;

import com.openwjk.central.commons.enums.ScheduledTaskEnum;
import com.openwjk.central.service.service.ScheduledService;
import com.openwjk.commons.exception.CommonsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author wangjunkai
 * @description
 * @date 2023/7/28 13:11
 */
@Service
@Slf4j
public class ScheduledFactory {
    @Autowired
    private List<ScheduledService> scheduledServiceList;

    private Map<ScheduledTaskEnum, ScheduledService> scheduledServiceMap;

    @PostConstruct
    private void init() {
        this.scheduledServiceMap = new HashMap<>();
        for (ScheduledService scheduledService : scheduledServiceList) {
            ScheduledTaskEnum scheduledEnum = scheduledService.getCode();
            if (Objects.nonNull(scheduledEnum)) {
                scheduledServiceMap.put(scheduledEnum, scheduledService);
            }
        }
    }

    public ScheduledService getScheduledService(ScheduledTaskEnum scheduledEnum) {
        ScheduledService indexCodeService = scheduledServiceMap.get(scheduledEnum);
        if (indexCodeService != null) {
            return indexCodeService;
        } else {
            log.warn("ScheduledFactory.getScheduledService:null,origin code:{}", scheduledEnum.getCode());
            throw new CommonsException(String.format("current code not exist: [%s]", scheduledEnum.getCode()));
        }
    }
}
