package com.openwjk.central.service.impl;

import com.openwjk.central.dao.mapper.FileDOMapperExt;
import com.openwjk.central.dao.mapper.MenuDOMapperExt;
import com.openwjk.central.dao.model.FileDO;
import com.openwjk.central.dao.model.FileDOExample;
import com.openwjk.central.dao.model.MenuDO;
import com.openwjk.central.dao.model.MenuDOExample;
import com.openwjk.central.service.domain.OssFile;
import com.openwjk.central.service.domain.req.FileReqVO;
import com.openwjk.central.service.service.FileService;
import com.openwjk.commons.exception.ParamInvalidException;
import com.openwjk.commons.utils.Constant;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.InputStream;
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
    @Autowired
    private MenuDOMapperExt menuDOMapperExt;
    @Autowired
    @Qualifier("minioService")
    private MinioService minioService;

    @Override
    public List<FileDO> getFileByGroupCode(String groupCode, Pageable pageable) {
        FileDOExample example = new FileDOExample();
        FileDOExample.Criteria criteria = example.createCriteria();
        criteria.andGroupCodeEqualTo(groupCode).andIsDeletedEqualTo(Constant.STR_N);
        return fileDOMapperExt.selectByExample(example);
    }

    @Override
    public void upload(InputStream inputStream, FileReqVO fileReqVO) {
        checkParam(inputStream, fileReqVO);
        OssFile ossFile = minioService.putObject(inputStream, fileReqVO.getBizCode(), fileReqVO.getFileName());
        insertFile(ossFile, fileReqVO);
    }

    private void insertFile(OssFile ossFile, FileReqVO fileReqVO) {
        FileDO fileDO = new FileDO();
        fileDO.setFileStoreBucket(ossFile.getBucket());
        fileDO.setContentType(fileReqVO.getContentType());
        fileDO.setFileStoreKey(ossFile.getObjectName());
        fileDO.setMd5Digest(ossFile.getMd5());
        fileDO.setGroupCode(fileReqVO.getBizCode() + Constant.SPLIT_UNION + fileReqVO.getCode());
        fileDO.setOriginalName(fileReqVO.getFileName());
        fileDOMapperExt.insert(fileDO);
    }

    private void checkParam(InputStream inputStream, FileReqVO fileReqVO) {
        if (inputStream == null)
            throw new ParamInvalidException("", "", null, "文件不能为空");
        if (fileReqVO == null)
            throw new ParamInvalidException("", "", null, "入参不能为空");
        if (StringUtils.isBlank(fileReqVO.getBizCode()))
            throw new ParamInvalidException("", "", null, "bizCode不能为空");
        if (fileReqVO.getCode() == null)
            throw new ParamInvalidException("", "", null, "code不能为空");
        if (StringUtils.isBlank(fileReqVO.getFileName()))
            throw new ParamInvalidException("", "", null, "文件名不能为空");
        if (CollectionUtils.isEmpty(getMenuByBizCodeAndId(fileReqVO.getBizCode(), fileReqVO.getCode())))
            throw new ParamInvalidException("", "", null, "未找到目标路径");
    }

    public List<MenuDO> getMenuByBizCodeAndId(String bizCode, Long code) {
        MenuDOExample example = new MenuDOExample();
        MenuDOExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(code).andBizCodeEqualTo(bizCode).andIsDeletedEqualTo(Constant.STR_N);
        return menuDOMapperExt.selectByExample(example);
    }
}
