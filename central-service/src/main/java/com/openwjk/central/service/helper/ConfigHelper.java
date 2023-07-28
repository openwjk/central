package com.openwjk.central.service.helper;

import com.google.common.collect.Lists;
import com.openwjk.central.dao.mapper.CtConfigDOMapper;
import com.openwjk.central.dao.model.CtConfigDO;
import com.openwjk.central.dao.model.CtConfigDOExample;
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
    CtConfigDOMapper configDOMapper;

    public CtConfigDO getConfigByGroupAndCode(String groupCode, String code) {
        CtConfigDOExample example = new CtConfigDOExample();
        example.createCriteria().andGroupCodeEqualTo(groupCode).andCodeEqualTo(code).andIsDeletedEqualTo(Constant.STR_N);
        List<CtConfigDO> configDOS = configDOMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(configDOS)) {
            return configDOS.get(Constant.INT_ZERO);
        }
        return null;
    }


    public List<CtConfigDO> getConfigByGroup(String groupCode) {
        CtConfigDOExample example = new CtConfigDOExample();
        example.createCriteria().andGroupCodeEqualTo(groupCode).andIsDeletedEqualTo(Constant.STR_N);
        List<CtConfigDO> configDOS = configDOMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(configDOS)) {
            return configDOS;
        }
        return Lists.newArrayList();
    }
}
