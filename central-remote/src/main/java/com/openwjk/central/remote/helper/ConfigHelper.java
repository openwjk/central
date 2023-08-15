package com.openwjk.central.remote.helper;

import com.google.common.collect.Lists;
import com.openwjk.central.dao.mapper.ConfigDOMapper;
import com.openwjk.central.dao.model.ConfigDO;
import com.openwjk.central.dao.model.ConfigDOExample;
import com.openwjk.commons.utils.Constant;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangjunkai
 * @description
 * @date 2023/7/28 9:15
 */
@Service
public class ConfigHelper {
    @Autowired
    ConfigDOMapper configDOMapper;

    public ConfigDO getConfigByGroupAndCode(String groupCode, String code) {
        ConfigDOExample example = new ConfigDOExample();
        example.createCriteria().andGroupCodeEqualTo(groupCode).andCodeEqualTo(code).andIsDeletedEqualTo(Constant.STR_N);
        List<ConfigDO> configDOS = configDOMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(configDOS)) {
            return configDOS.get(Constant.INT_ZERO);
        }
        return null;
    }


    public List<ConfigDO> getConfigByGroup(String groupCode) {
        ConfigDOExample example = new ConfigDOExample();
        example.createCriteria().andGroupCodeEqualTo(groupCode).andIsDeletedEqualTo(Constant.STR_N);
        List<ConfigDO> configDOS = configDOMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(configDOS)) {
            return configDOS;
        }
        return Lists.newArrayList();
    }
}
