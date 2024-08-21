package com.openwjk.central.service.service;

import com.openwjk.central.dao.model.FileDO;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author wangjunkai
 * @description
 * @date 2024/8/6 19:50
 */
public interface FileService {

    List<FileDO> getFileByGroupCode(String groupCode, Pageable pageable);
}
