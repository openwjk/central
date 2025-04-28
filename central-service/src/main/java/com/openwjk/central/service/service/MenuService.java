package com.openwjk.central.service.service;

import com.openwjk.central.commons.domain.Dict;

import java.util.List;

/**
 * @author junkai.wang
 * @date 2025/4/24 15:05
 * @description
 */

public interface MenuService {
    /**
     * 获取微信小程序相册
     * @return
     */
    List<Dict> getWxminiAlbum();
}
