package com.openwjk.central.service.impl;

import com.openwjk.central.dao.model.FileDO;
import com.openwjk.central.remote.dto.response.CommonQueryRespDTO;
import com.openwjk.central.remote.dto.response.WxminiSessionRespDTO;
import com.openwjk.central.remote.service.WxminiService;
import com.openwjk.central.service.domain.resp.WxminiPhotoVO;
import com.openwjk.central.service.service.FileService;
import com.openwjk.central.service.service.WxminiProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author junkai.wang
 * @date 2025/4/23 22:11
 * @description TODO
 */
@Service
@RequiredArgsConstructor
public class WxminiProgramServiceImpl implements WxminiProgramService {
    private final WxminiService wxminiService;
    private final FileService fileService;

    @Override
    public WxminiSessionRespDTO getSession(String jsCode) {
        CommonQueryRespDTO<WxminiSessionRespDTO> commonQueryRespDTO = wxminiService.getSession(jsCode);
        return commonQueryRespDTO.getRespEntry();
    }

    @Override
    public List<WxminiPhotoVO> getPhotos(String groupCode) {
        List<FileDO> fileDOS = fileService.getFileByGroupCode(groupCode, PageRequest.of(1, 100));
        return fileDOS.stream()
                .map(i -> new WxminiPhotoVO(i.getId(), i.getOriginalName(), fileService.getUrl("wxminialbum", i.getFileStoreKey())))
                .collect(Collectors.toList());
    }


}
