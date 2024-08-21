package com.openwjk.central.dao.mapper;

import com.openwjk.central.dao.model.MenuDO;
import com.openwjk.central.dao.model.MenuDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MenuDOMapper {
    long countByExample(MenuDOExample example);

    int insert(MenuDO record);

    int insertSelective(MenuDO record);

    List<MenuDO> selectByExample(MenuDOExample example);

    MenuDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MenuDO record, @Param("example") MenuDOExample example);

    int updateByExample(@Param("record") MenuDO record, @Param("example") MenuDOExample example);

    int updateByPrimaryKeySelective(MenuDO record);

    int updateByPrimaryKey(MenuDO record);
}