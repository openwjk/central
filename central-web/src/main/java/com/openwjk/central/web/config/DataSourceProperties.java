package com.openwjk.central.web.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/31 14:49
 */
@Component
@ConfigurationProperties("spring.datasource")
@Data
public class DataSourceProperties {
    private List<ShardingJdbc> shardingJdbc;
    @Data
    public static class ShardingJdbc {
        private String name;
        private String url;
        private String username;
        private String password;
        private String driverClassName;
        private String filters;
        private int maxActive;
        private int initialSize;
        private long maxWait;
        private int minIdle;
        private long timeBetweenEvictionRunsMillis;
        private long minEvictableIdleTimeMillis;
        private String validationQuery;
        private boolean testWhileIdle;
        private boolean testOnBorrow;
        private boolean testOnReturn;
        private boolean poolPreparedStatements;
        private String connectionProperties;
    }
}
