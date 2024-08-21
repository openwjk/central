package com.openwjk.central.dao.mapper;

import com.openwjk.central.dao.model.FileDO;
import com.openwjk.central.dao.model.FileDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FileDOMapper {
    long countByExample(FileDOExample example);

    int insert(FileDO record);

    int insertSelective(FileDO record);

    List<FileDO> selectByExample(FileDOExample example);

    FileDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FileDO record, @Param("example") FileDOExample example);

    int updateByExample(@Param("record") FileDO record, @Param("example") FileDOExample example);

    int updateByPrimaryKeySelective(FileDO record);

    int updateByPrimaryKey(FileDO record);
}