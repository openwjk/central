package com.openwjk.central.service.service;

import java.util.Map;

/**
 * @author wangjunkai
 * @description
 * @date 2023/12/21 15:32
 */
public interface IParamStrategy {
    String primaryKey();

    boolean determine(Map<String, Object> param);
}
