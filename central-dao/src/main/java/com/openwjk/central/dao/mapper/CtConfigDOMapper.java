package com.openwjk.central.dao.mapper;

import com.openwjk.central.dao.model.CtConfigDO;
import com.openwjk.central.dao.model.CtConfigDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CtConfigDOMapper {
    long countByExample(CtConfigDOExample example);

    int insert(CtConfigDO record);

    int insertSelective(CtConfigDO record);

    List<CtConfigDO> selectByExample(CtConfigDOExample example);

    CtConfigDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CtConfigDO record, @Param("example") CtConfigDOExample example);

    int updateByExample(@Param("record") CtConfigDO record, @Param("example") CtConfigDOExample example);

    int updateByPrimaryKeySelective(CtConfigDO record);

    int updateByPrimaryKey(CtConfigDO record);
}