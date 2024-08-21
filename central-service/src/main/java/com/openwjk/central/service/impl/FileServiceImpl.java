package com.openwjk.central.service.impl;

import com.openwjk.central.dao.model.FileDO;
import com.openwjk.central.service.service.FileService;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangjunkai
 * @description
 * @date 2024/8/21 15:31
 */
public class FileServiceImpl implements FileService {
    @Override
    public List<FileDO> getFileByGroupCode(String groupCode, Pageable pageable) {
        return null;
    }
}
