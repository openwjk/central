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
public class DataSourceProperties {
    private List<ShardingJdbc> shardingJdbc;

    public List<ShardingJdbc> getShardingJdbc() {
        return shardingJdbc;
    }

    public void setShardingJdbc(List<ShardingJdbc> shardingJdbc) {
        this.shardingJdbc = shardingJdbc;
    }

    public static class ShardingJdbc {
        private String name;
        private String url;
        private String username;
        private String password;
        private String driverClassName;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getDriverClassName() {
            return driverClassName;
        }

        public void setDriverClassName(String driverClassName) {
            this.driverClassName = driverClassName;
        }
    }
}
