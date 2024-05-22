package com.cc.smsuser;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

// 开启Redis缓存
@EnableCaching
// 开启服务发现，注册到Nacos
@EnableDiscoveryClient
// Spring Boot使用Mybatis扫描映射配置文件dao接口
@MapperScan("com.cc.smsuser.dao")
@SpringBootApplication
public class SmsUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmsUserApplication.class, args);
    }

}
