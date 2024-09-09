package com.openwjk.central.commons.config;

import com.openwjk.central.commons.factory.CentralThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/3 15:04
 */
@Configuration
public class TaskThreadPoolConfig {
    @Bean("pool")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setCorePoolSize(10);
        pool.setKeepAliveSeconds(300);
        pool.setMaxPoolSize(20);
        pool.setQueueCapacity(Integer.MAX_VALUE);
        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        pool.setThreadFactory(CentralThreadFactory.create("通用", false));
        return pool;
    }
}
