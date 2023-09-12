package com.openwjk.central.dao.mapper;

import com.openwjk.central.dao.model.MapDO;
import com.openwjk.central.dao.model.MapDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MapDOMapper {
    long countByExample(MapDOExample example);

    int insert(MapDO record);

    int insertSelective(MapDO record);

    List<MapDO> selectByExample(MapDOExample example);

    int updateByExampleSelective(@Param("record") MapDO record, @Param("example") MapDOExample example);

    int updateByExample(@Param("record") MapDO record, @Param("example") MapDOExample example);
}