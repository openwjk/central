package com.openwjk.central.dao.mapper;

import com.openwjk.central.dao.model.ConfigDO;
import com.openwjk.central.dao.model.ConfigDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ConfigDOMapper {
    long countByExample(ConfigDOExample example);

    int insert(ConfigDO record);

    int insertSelective(ConfigDO record);

    List<ConfigDO> selectByExample(ConfigDOExample example);

    ConfigDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ConfigDO record, @Param("example") ConfigDOExample example);

    int updateByExample(@Param("record") ConfigDO record, @Param("example") ConfigDOExample example);

    int updateByPrimaryKeySelective(ConfigDO record);

    int updateByPrimaryKey(ConfigDO record);
}