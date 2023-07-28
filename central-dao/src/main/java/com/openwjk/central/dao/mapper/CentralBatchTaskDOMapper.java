package com.openwjk.central.dao.mapper;

import com.openwjk.central.dao.model.CentralBatchTaskDO;
import com.openwjk.central.dao.model.CentralBatchTaskDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CentralBatchTaskDOMapper {
    long countByExample(CentralBatchTaskDOExample example);

    int insert(CentralBatchTaskDO record);

    int insertSelective(CentralBatchTaskDO record);

    List<CentralBatchTaskDO> selectByExample(CentralBatchTaskDOExample example);

    CentralBatchTaskDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CentralBatchTaskDO record, @Param("example") CentralBatchTaskDOExample example);

    int updateByExample(@Param("record") CentralBatchTaskDO record, @Param("example") CentralBatchTaskDOExample example);

    int updateByPrimaryKeySelective(CentralBatchTaskDO record);

    int updateByPrimaryKey(CentralBatchTaskDO record);
}