package com.openwjk.central.service.config;

import com.openwjk.central.service.impl.MinioService;
import io.minio.MinioClient;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangjunkai
 * @description
 * @date 2024/8/22 14:52
 */
@Configuration
public class MinioConfig {
    @Value(value = "${minio.host}")
    private String minioHost;
    @Value(value = "${minio.access-key}")
    private String accessKey;
    @Value(value = "${minio.secret-key}")
    private String secretKey;
    @Value(value = "${minio.bucket}")
    private String bucketName;

    public String getBucketName() {
        return bucketName;
    }

    //  将 MinIOClient 注入到 Spring 上下文中
    @Bean("minioClient")
    public MinioClient minioClient() {
        return MinioClient.builder().endpoint(minioHost).credentials(accessKey, secretKey).build();
    }

    //  初始化MinioTemplate，封装了一些MinIOClient的基本操作
    @Bean(name = "minioService")
    public MinioService minioService() {
        return new MinioService(minioClient(), this);
    }
}
