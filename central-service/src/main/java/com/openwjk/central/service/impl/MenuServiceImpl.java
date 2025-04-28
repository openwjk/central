package com.openwjk.central.service.impl;

import com.openwjk.central.commons.domain.Dict;
import com.openwjk.central.commons.utils.Constants;
import com.openwjk.central.dao.mapper.MenuDOMapperExt;
import com.openwjk.central.dao.model.MenuDO;
import com.openwjk.central.dao.model.MenuDOExample;
import com.openwjk.central.service.service.MenuService;
import com.openwjk.commons.utils.Constant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author junkai.wang
 * @date 2025/4/24 15:09
 * @description TODO
 */
@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {
    private static final String WXMINI_ALBUM = "WXMINI_ALBUM";
    private final MenuDOMapperExt menuDOMapperExt;

    @Override
    public List<Dict> getWxminiAlbum() {
        MenuDOExample example = new MenuDOExample();
        example.createCriteria()
                .andBizCodeEqualTo(WXMINI_ALBUM)
                .andIsDeletedEqualTo(Constant.STR_N);
        return menuDOMapperExt.selectByExample(example).stream()
                .sorted(Comparator.comparing(MenuDO::getSort))
                .map(i -> new Dict(i.getId().toString(), i.getName()))
                .collect(Collectors.toList());
    }
}
