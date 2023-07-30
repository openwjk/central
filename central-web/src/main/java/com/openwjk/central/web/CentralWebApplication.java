package com.openwjk.central.web;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;


@SpringBootApplication
@ComponentScan("com.openwjk.central")
@EnableScheduling
@EnableCaching
@EnableSwagger2WebMvc
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
