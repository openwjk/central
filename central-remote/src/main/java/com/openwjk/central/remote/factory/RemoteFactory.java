package com.openwjk.central.remote.factory;

import com.openwjk.central.commons.enums.ScheduledTaskEnum;
import com.openwjk.central.remote.enums.RemoteTypeEnum;
import com.openwjk.central.remote.service.IDataService;
import com.openwjk.central.remote.service.IRemoteService;
import com.openwjk.commons.exception.CommonsException;
import javafx.concurrent.ScheduledService;
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
 * @date 2023/7/30 10:12
 */
@Service
@Slf4j
public class RemoteFactory {
    @Autowired
    private List<IDataService> dataServiceList;
    @Autowired
    private List<IRemoteService> remoteServiceList;

    private Map<RemoteTypeEnum, IDataService> dataServiceMap;
    private Map<RemoteTypeEnum, IRemoteService> remoteServiceMap;

    @PostConstruct
    private void init() {
        this.dataServiceMap = new HashMap<>();
        for (IDataService dataService : dataServiceList) {
            RemoteTypeEnum remoteTypeEnum = dataService.getCode();
            if (Objects.nonNull(remoteTypeEnum)) {
                dataServiceMap.put(remoteTypeEnum, dataService);
            }
        }
        this.remoteServiceMap = new HashMap<>();
        for (IRemoteService remoteService : remoteServiceList) {
            RemoteTypeEnum remoteTypeEnum = remoteService.getCode();
            if (Objects.nonNull(remoteTypeEnum)) {
                remoteServiceMap.put(remoteTypeEnum, remoteService);
            }
        }
    }

    public IDataService getDataService(RemoteTypeEnum remoteTypeEnum) {
        IDataService dataService = dataServiceMap.get(remoteTypeEnum);
        if (dataService != null) {
            return dataService;
        } else {
            throw new CommonsException(String.format("RemoteFactory.getDataService code not exist: [%s]", remoteTypeEnum.name()));
        }
    }
    public IRemoteService getRemoteService(RemoteTypeEnum remoteTypeEnum) {
        IRemoteService remoteService = remoteServiceMap.get(remoteTypeEnum);
        if (remoteService != null) {
            return remoteService;
        } else {
            throw new CommonsException(String.format("RemoteFactory.getRemoteService code not exist: [%s]", remoteTypeEnum.name()));
        }
    }
}
