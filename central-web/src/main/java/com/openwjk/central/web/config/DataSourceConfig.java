package com.openwjk.central.web.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.google.common.collect.Lists;
import com.openwjk.central.commons.utils.IpUtils;
import com.openwjk.central.commons.utils.RedisLockUtil;
import com.openwjk.central.commons.utils.RedisUtil;
import com.openwjk.commons.utils.Constant;
import com.openwjk.commons.utils.RandomCodeUtil;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.config.algorithm.AlgorithmConfiguration;
import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableReferenceRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.keygen.KeyGenerateStrategyConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.StandardShardingStrategyConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/31 14:10
 */
@Configuration
public class DataSourceConfig {
    private static final String SNOWFLAKE_WORK_ID = "SNOWFLAKE_WORK_ID";
    private static final String SNOWFLAKE_WORK_ID_LOCK = "SNOWFLAKE_WORK_ID_LOCK";
    @Autowired
    DataSourceProperties dataSourceProperties;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    private RedisLockUtil redisLockUtil;

    @Bean
    public DataSource dataSource() {
        // 配置真实数据源
        Map<String, DataSource> dataSourceMap = createDataSources();
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        // 配置分片规则
        setTableRuleConfig(shardingRuleConfig);
        //算法配置
        setShardingAlgorithms(shardingRuleConfig);
        //配置绑定关系，避免出现笛卡尔集
        shardingRuleConfig.getBindingTableGroups().add(new ShardingTableReferenceRuleConfiguration("ct_account", "ct_account_type"));

        DataSource dataSource = null;
        try {
            dataSource = ShardingSphereDataSourceFactory.createDataSource(dataSourceMap, Lists.newArrayList(shardingRuleConfig), new Properties());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    private void setShardingAlgorithms(ShardingRuleConfiguration shardingRuleConfig) {
        shardingRuleConfig.getShardingAlgorithms().put("accountShardingAlgorithm", getAlgorithmConfig("INLINE", "accountShardingAlgorithm"));
        shardingRuleConfig.getShardingAlgorithms().put("accountTypeShardingAlgorithm", getAlgorithmConfig("INLINE", "accountTypeShardingAlgorithm"));
        shardingRuleConfig.getShardingAlgorithms().put("accountDBShardingAlgorithm", getAlgorithmConfig("CLASS_BASED", "accountDBShardingAlgorithm"));
        shardingRuleConfig.getKeyGenerators().put("customSnowflakeAlgorithm", getAlgorithmConfig("CUSTOM_SNOWFLAKE", "customSnowflakeAlgorithm"));
    }

    //获取算法配置
    private AlgorithmConfiguration getAlgorithmConfig(String type, String code) {
        Properties properties = new Properties();
        switch (code) {
            case "accountShardingAlgorithm":
                properties.setProperty("algorithm-expression", "ct_account_${ID % 3}");
                break;
            case "accountTypeShardingAlgorithm":
                properties.setProperty("algorithm-expression", "ct_account_type_${ID % 3}");
                break;
            case "accountDBShardingAlgorithm":
                properties.setProperty("strategy", "STANDARD");
                properties.setProperty("algorithmClassName", "com.openwjk.central.sje.algorithm.DbShardingAlgorithm");
                break;
            case "snowflakeAlgorithm":
                properties.setProperty("worker-id", getWorkId());
                properties.setProperty("max-tolerate-time-difference-milliseconds", Constant.STRING_ZERO);
                break;
            case "customSnowflakeAlgorithm":
                break;
            default:
                break;
        }

        return new AlgorithmConfiguration(type, properties);
    }

    @SneakyThrows
    private String getWorkId() {
        String value = RandomCodeUtil.generateCode(Constant.INT_TEN);
        try {
            if (redisLockUtil.tryLock(SNOWFLAKE_WORK_ID_LOCK, value, Constant.INT_THIRTY)) {
                String ip = IpUtils.getIp();
                String ipsStr = redisUtil.get(SNOWFLAKE_WORK_ID);
                List<String> ips = Lists.newArrayList();
                if (StringUtils.isNotBlank(ipsStr)) {
                    ips = JSONArray.parseArray(ipsStr, String.class);
                    if (ips.contains(ip)) {
                        return String.valueOf(ips.indexOf(ip) + Constant.INT_ONE);
                    } else {
                        ips.add(ip);
                        redisUtil.set(SNOWFLAKE_WORK_ID, JSON.toJSONString(ips));
                        return String.valueOf(ips.size());
                    }

                } else {
                    ips.add(ip);
                    redisUtil.set(SNOWFLAKE_WORK_ID, JSON.toJSONString(ips));
                    return String.valueOf(ips.size());
                }
            } else {
                throw new Exception("获取锁失败");
            }
        } finally {
            redisLockUtil.releaseLock(SNOWFLAKE_WORK_ID_LOCK, value);
        }
    }

    // 配置分片规则
    //表配置
    private void setTableRuleConfig(ShardingRuleConfiguration shardingRuleConfig) {

        //配置逻辑表映射
        ShardingTableRuleConfiguration accountTableRuleConfig = new ShardingTableRuleConfiguration("ct_account", "ds${0..1}.ct_account_${0..2}");
        ShardingTableRuleConfiguration accountTypeTableRuleConfig = new ShardingTableRuleConfiguration("ct_account_type", "ds${0..1}.ct_account_type_${0..2}");
        // 配置分表策略
        accountTableRuleConfig.setTableShardingStrategy(new StandardShardingStrategyConfiguration("ID", "accountShardingAlgorithm"));
        accountTypeTableRuleConfig.setTableShardingStrategy(new StandardShardingStrategyConfiguration("ID", "accountTypeShardingAlgorithm"));
        // 配置分库策略
        accountTableRuleConfig.setDatabaseShardingStrategy(new StandardShardingStrategyConfiguration("ID", "accountDBShardingAlgorithm"));
        accountTypeTableRuleConfig.setDatabaseShardingStrategy(new StandardShardingStrategyConfiguration("ID", "accountDBShardingAlgorithm"));
        // 配置id生成策略
        //结合业务思考，如果并发不大不建议使用自带的雪花id，并发不大的情况下生成的几乎全为偶数
//        accountTableRuleConfig.setKeyGenerateStrategy(new KeyGenerateStrategyConfiguration("ID", "snowflakeAlgorithm"));
//        accountTypeTableRuleConfig.setKeyGenerateStrategy(new KeyGenerateStrategyConfiguration("ID", "snowflakeAlgorithm"));
        accountTableRuleConfig.setKeyGenerateStrategy(new KeyGenerateStrategyConfiguration("ID", "customSnowflakeAlgorithm"));
        accountTypeTableRuleConfig.setKeyGenerateStrategy(new KeyGenerateStrategyConfiguration("ID", "customSnowflakeAlgorithm"));
        shardingRuleConfig.getTables().addAll(Lists.newArrayList(accountTableRuleConfig, accountTypeTableRuleConfig));
    }

    // 配置真实数据源
    private Map<String, DataSource> createDataSources() {
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        for (int i = 0; i < dataSourceProperties.getShardingJdbc().size(); i++) {
            DruidDataSource dataSource = getDataSource(dataSourceProperties.getShardingJdbc().get(i));
            dataSourceMap.put("ds" + i, dataSource);
        }
        return dataSourceMap;
    }

    @SneakyThrows
    private DruidDataSource getDataSource(DataSourceProperties.ShardingJdbc shardingJdbc) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(shardingJdbc.getDriverClassName());
        dataSource.setUrl(shardingJdbc.getUrl());
        dataSource.setUsername(shardingJdbc.getUsername());
        dataSource.setPassword(shardingJdbc.getPassword());
        dataSource.setFilters(shardingJdbc.getFilters());
        dataSource.setMaxActive(shardingJdbc.getMaxActive());
        dataSource.setInitialSize(shardingJdbc.getInitialSize());
        dataSource.setMaxWait(shardingJdbc.getMaxWait());
        dataSource.setMinIdle(shardingJdbc.getMinIdle());
        dataSource.setTimeBetweenEvictionRunsMillis(shardingJdbc.getTimeBetweenEvictionRunsMillis());
        dataSource.setMinEvictableIdleTimeMillis(shardingJdbc.getMinEvictableIdleTimeMillis());
        dataSource.setValidationQuery(shardingJdbc.getValidationQuery());
        dataSource.setTestWhileIdle(shardingJdbc.isTestWhileIdle());
        dataSource.setTestOnBorrow(shardingJdbc.isTestOnBorrow());
        dataSource.setTestOnReturn(shardingJdbc.isTestOnReturn());
        dataSource.setPoolPreparedStatements(shardingJdbc.isPoolPreparedStatements());
        dataSource.setConnectProperties(getProperties(shardingJdbc.getConnectionProperties()));
        return dataSource;
    }

    public Properties getProperties(String connectionProperties) {
        if (connectionProperties == null || connectionProperties.trim().length() == 0) {
            return null;
        }
        String[] entries = connectionProperties.split(";");
        Properties properties = new Properties();
        for (int i = 0; i < entries.length; i++) {
            String entry = entries[i];
            if (entry.length() > 0) {
                int index = entry.indexOf('=');
                if (index > 0) {
                    String name = entry.substring(0, index);
                    String value = entry.substring(index + 1);
                    properties.setProperty(name, value);
                } else {
                    // no value is empty string which is how java.util.Properties works
                    properties.setProperty(entry, "");
                }
            }
        }
        return properties;
    }
}
