package com.openwjk.central.web;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan("com.openwjk.central")
@EnableScheduling
@EnableCaching
@EnableDubbo(scanBasePackages = {"com.openwjk.central"})
@EnableSwagger2WebMvc
@EnableTransactionManagement
@MapperScan(basePackages = {"com.openwjk.central.dao.mapper"}, annotationClass = Mapper.class)
public class CentralWebApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(CentralWebApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(CentralWebApplication.class);
    }
}
