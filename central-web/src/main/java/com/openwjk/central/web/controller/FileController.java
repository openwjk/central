package com.openwjk.central.web.controller;

import com.openwjk.central.service.domain.req.CtConfigReqVO;
import com.openwjk.central.service.domain.req.FileReqVO;
import com.openwjk.central.service.service.FileService;
import com.openwjk.commons.domain.ResponseVO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author wangjunkai
 * @description
 * @date 2024/8/23 17:14
 */
@RestController
@RequestMapping("/config")
@ApiOperation("文件")
public class FileController {
    @Autowired
    FileService fileService;

    @PostMapping("/upload")
    @ApiOperation("上传文件")
    @SneakyThrows
    public ResponseVO upload(@ApiParam(name = "file", type = "multipart/form-data") MultipartFile file, @RequestBody FileReqVO fileReqVO) {
        if (StringUtils.isBlank(file.getContentType()))
            fileReqVO.setContentType(file.getContentType());
        if (StringUtils.isBlank(file.getOriginalFilename()))
            fileReqVO.setContentType(file.getOriginalFilename());
        fileService.upload(file.getInputStream(), fileReqVO);
        return new ResponseVO();
    }
}
