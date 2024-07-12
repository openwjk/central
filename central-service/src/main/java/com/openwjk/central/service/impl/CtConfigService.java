package com.openwjk.central.service.impl;

import com.openwjk.central.dao.mapper.ConfigDOMapperExt;
import com.openwjk.central.dao.model.ConfigDO;
import com.openwjk.central.service.domain.req.CtConfigReqVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangjunkai
 * @description
 * @date 2024/7/12 16:44
 */
@Service
public class CtConfigService {
    @Autowired
    ConfigDOMapperExt configDOMapperExt;

    public void addConfig(CtConfigReqVO reqVO) {
        ConfigDO config = new ConfigDO();
        BeanUtils.copyProperties(reqVO, config);
        configDOMapperExt.insert(config);
    }
}
