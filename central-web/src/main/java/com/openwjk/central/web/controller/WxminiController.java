package com.openwjk.central.web.controller;

import com.openwjk.central.commons.annotation.ApiLog;
import com.openwjk.central.remote.dto.response.WxminiSessionRespDTO;
import com.openwjk.central.service.domain.resp.WxminiPhotoVO;
import com.openwjk.central.service.service.WxService;
import com.openwjk.central.service.service.WxminiProgramService;
import com.openwjk.commons.domain.ResponseVO;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;
import java.util.List;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/6 19:43
 */
@RestController
@RequestMapping("/wxmini")
@ApiOperation("微信系统控制层")
@Log4j2
@RequiredArgsConstructor
public class WxminiController {
    private final WxminiProgramService wxminiProgramService;

    @GetMapping("/getSession")
    @ApiLog(standartReturn = false)
    public WxminiSessionRespDTO getSession(@RequestParam("jsCode") String jsCode) {
        log.info("jsCode: {} ", jsCode);
        return wxminiProgramService.getSession(jsCode);
    }

    @GetMapping("/getPhotos")
    @ApiLog(standartReturn = false)
    public ResponseVO<List<WxminiPhotoVO>> getPhotos(@RequestParam("groupCode") String groupCode) {
        log.info("groupCode: {} ", groupCode);
        return new ResponseVO<>(wxminiProgramService.getPhotos(groupCode));
    }

}
