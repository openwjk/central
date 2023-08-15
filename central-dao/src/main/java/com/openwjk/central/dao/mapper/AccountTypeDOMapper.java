package com.openwjk.central.dao.mapper;

import com.openwjk.central.dao.model.AccountTypeDO;
import com.openwjk.central.dao.model.AccountTypeDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccountTypeDOMapper {
    long countByExample(AccountTypeDOExample example);

    int insert(AccountTypeDO record);

    int insertSelective(AccountTypeDO record);

    List<AccountTypeDO> selectByExample(AccountTypeDOExample example);

    AccountTypeDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AccountTypeDO record, @Param("example") AccountTypeDOExample example);

    int updateByExample(@Param("record") AccountTypeDO record, @Param("example") AccountTypeDOExample example);

    int updateByPrimaryKeySelective(AccountTypeDO record);

    int updateByPrimaryKey(AccountTypeDO record);
}