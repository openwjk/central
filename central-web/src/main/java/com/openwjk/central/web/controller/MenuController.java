package com.openwjk.central.web.controller;

import com.openwjk.central.commons.annotation.ApiLog;
import com.openwjk.central.commons.domain.Dict;
import com.openwjk.central.remote.dto.response.WxminiSessionRespDTO;
import com.openwjk.central.service.service.MenuService;
import com.openwjk.central.service.service.WxminiProgramService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/6 19:43
 */
@RestController
@RequestMapping("/menu")
@ApiOperation("菜单")
@Log4j2
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;

    @GetMapping("/getWxminiAlbum")
    public List<Dict> getWxminiAlbum() {
        return menuService.getWxminiAlbum();
    }

}
