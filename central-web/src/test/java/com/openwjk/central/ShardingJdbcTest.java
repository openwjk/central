package com.openwjk.central;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.openwjk.central.dao.mapper.ConfigDOMapperExt;
import com.openwjk.central.dao.model.ConfigDO;
import com.openwjk.central.dao.model.ConfigDOExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


/**
 * @author wangjunkai
 * @description
 * @date 2023/7/29 8:09
 */
@SpringBootTest(classes = BaseTest.class)
@RunWith(SpringRunner.class)
public class ShardingJdbcTest {
    @Autowired
    ConfigDOMapperExt configDOMapperExt;

    @Test
    public void test() {
        PageHelper.startPage(1, 10);
        ConfigDOExample example = new ConfigDOExample();
        example.createCriteria();
        List<ConfigDO> list = configDOMapperExt.selectByExample(example);
        PageInfo<ConfigDO> pageInfo = new PageInfo<>(list);
        System.out.println(1);

        List<ConfigDO> list2 = configDOMapperExt.selectByExample(example);
        System.out.println(1);
    }
}
