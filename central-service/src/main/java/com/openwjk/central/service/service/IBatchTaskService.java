package com.openwjk.central.service.service;


import com.openwjk.central.dao.model.CentralBatchTaskDO;
import com.openwjk.central.service.enums.BatchTaskTypeEnum;

import java.util.Date;
import java.util.Map;

/**
 * @author wangjunkai
 * @description
 * @date 2023/7/28 13:15
 */
public interface IBatchTaskService {
    BatchTaskTypeEnum getTaskType();

    boolean preCheck(CentralBatchTaskDO task);

    Map<String, Object> buildTaskContext();

    void beforeTask(CentralBatchTaskDO task, Map<String, Object> context);

    Object doTask(CentralBatchTaskDO task, Map<String, Object> context);

    void afterTask(CentralBatchTaskDO task, Map<String, Object> context, Object result);

    boolean whetherDoTask(CentralBatchTaskDO task, Map<String, Object> context);

    boolean isSuccess(Object result);

    boolean isNeedRetry(Object result);

    Date nextStartTime(CentralBatchTaskDO task);

    int getMaxRetryTime();

    void retryTooMuch(CentralBatchTaskDO task);
}
