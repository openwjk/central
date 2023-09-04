package com.openwjk.central.web.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.collect.Lists;
import com.openwjk.commons.utils.Constant;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.config.algorithm.AlgorithmConfiguration;
import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableReferenceRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.StandardShardingStrategyConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/31 14:10
 */
@Configuration
public class DataSourceConfig {
    @Autowired
    DataSourceProperties dataSourceProperties;

    @Bean
    @Primary
    public DataSource dataSource() {
        // 配置真实数据源
        Map<String, DataSource> dataSourceMap = createDataSources();
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTables().add(getTableRuleConfig());

        // 配置表
//        ShardingTableRuleConfiguration accountTableRuleConfig = new ShardingTableRuleConfiguration("ct_account", "ds0.ct_account_${0..1}");
//        ShardingTableRuleConfiguration accountTypeTableRuleConfig = new ShardingTableRuleConfiguration("ct_account_type", "ds0.ct_account_type_${0..1}");
//        // 配置分表策略
//        accountTableRuleConfig.setTableShardingStrategy(new StandardShardingStrategyConfiguration("U_ID", "accountShardingAlgorithm"));
//        accountTypeTableRuleConfig.setTableShardingStrategy(new StandardShardingStrategyConfiguration("U_ID", "accountTypeShardingAlgorithm"));
//        // 配置分表算法
//        Properties accountShardingAlgorithmrProps = new Properties();
//        accountShardingAlgorithmrProps.setProperty("algorithm-expression", "ct_account_${U_ID % 2}");
//        Properties accountTypeShardingAlgorithmrProps = new Properties();
//        accountTypeShardingAlgorithmrProps.setProperty("algorithm-expression", "ct_account_type_${U_ID % 2}");
//        // 配置分片规则
//        ShardingRuleConfiguration tableShardingRuleConfig = new ShardingRuleConfiguration();
//        tableShardingRuleConfig.getTables().add(accountTableRuleConfig);
//        tableShardingRuleConfig.getShardingAlgorithms()
//                .put("accountShardingAlgorithm", new AlgorithmConfiguration("INLINE", accountShardingAlgorithmrProps));
//
//        tableShardingRuleConfig.getTables().add(accountTypeTableRuleConfig);
//        tableShardingRuleConfig.getShardingAlgorithms().put("accountTypeShardingAlgorithm", new AlgorithmConfiguration("INLINE", accountTypeShardingAlgorithmrProps));
//
//        //配置绑定关系，避免出现笛卡尔集
//        tableShardingRuleConfig.getBindingTableGroups()
//                .add(new ShardingTableReferenceRuleConfiguration("ct_account", "ct_account_type"));


//        // 配置分库策略
//        accountTableRuleConfig.setDatabaseShardingStrategy(new StandardShardingStrategyConfiguration("U_ID", "dbShardingAlgorithm"));
//        // 配置分库算法
//        Properties dbShardingAlgorithmrProps = new Properties();
//        dbShardingAlgorithmrProps.setProperty("algorithm-expression", "ds${U_ID % 2}");
//        tableShardingRuleConfig.getShardingAlgorithms().put("dbShardingAlgorithm", new AlgorithmConfiguration("INLINE", dbShardingAlgorithmrProps));

        DataSource dataSource = null;
//        try {
////            dataSource = ShardingSphereDataSourceFactory.createDataSource(dataSourceMap, Lists.newArrayList(tableShardingRuleConfig), new Properties());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return dataSource;
    }

    private ShardingTableRuleConfiguration getTableRuleConfig() {
        ShardingTableRuleConfiguration accountTableRuleConfig = new ShardingTableRuleConfiguration("ct_account", "ds${0..1}.ct_account_${0..1}");
        accountTableRuleConfig.setTableShardingStrategy(new StandardShardingStrategyConfiguration("U_ID", "accountShardingAlgorithm"));
        accountTableRuleConfig.setDatabaseShardingStrategy(new StandardShardingStrategyConfiguration("U_ID", "accountDbShardingAlgorithm"));
        return accountTableRuleConfig;
    }

    private Map<String, DataSource> createDataSources() {
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        // 配置第 1 个数据源
        DruidDataSource dataSource1 = new DruidDataSource();
        dataSource1.setDriverClassName(dataSourceProperties.getShardingJdbc().get(Constant.INT_ZERO).getDriverClassName());
        dataSource1.setUrl(dataSourceProperties.getShardingJdbc().get(Constant.INT_ZERO).getUrl());
        dataSource1.setUsername(dataSourceProperties.getShardingJdbc().get(Constant.INT_ZERO).getUsername());
        dataSource1.setPassword(dataSourceProperties.getShardingJdbc().get(Constant.INT_ZERO).getPassword());
        dataSourceMap.put("ds0", dataSource1);

        // 配置第 2 个数据源
        DruidDataSource dataSource2 = new DruidDataSource();
        dataSource1.setDriverClassName(dataSourceProperties.getShardingJdbc().get(Constant.INT_ONE).getDriverClassName());
        dataSource1.setUrl(dataSourceProperties.getShardingJdbc().get(Constant.INT_ONE).getUrl());
        dataSource1.setUsername(dataSourceProperties.getShardingJdbc().get(Constant.INT_ONE).getUsername());
        dataSource1.setPassword(dataSourceProperties.getShardingJdbc().get(Constant.INT_ONE).getPassword());
        dataSourceMap.put("ds1", dataSource2);
        return dataSourceMap;
    }
}
