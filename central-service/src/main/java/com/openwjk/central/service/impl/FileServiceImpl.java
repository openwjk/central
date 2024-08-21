package com.openwjk.central.service.impl;

import com.github.pagehelper.PageHelper;
import com.openwjk.central.commons.utils.Constants;
import com.openwjk.central.dao.mapper.FileDOMapperExt;
import com.openwjk.central.dao.model.FileDO;
import com.openwjk.central.dao.model.FileDOExample;
import com.openwjk.central.service.service.FileService;
import com.openwjk.commons.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangjunkai
 * @description
 * @date 2024/8/21 15:31
 */
@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileDOMapperExt fileDOMapperExt;

    @Override
    public List<FileDO> getFileByGroupCode(String groupCode, Pageable pageable) {
        if (pageable != null)
            PageHelper.startPage(pageable.getPageNumber(), pageable.getPageSize());
        FileDOExample example = new FileDOExample();
        FileDOExample.Criteria criteria = example.createCriteria();
        criteria.andGroupCodeEqualTo(groupCode).andIsDeletedEqualTo(Constant.STR_N);
        return fileDOMapperExt.selectByExample(example);
    }
}
