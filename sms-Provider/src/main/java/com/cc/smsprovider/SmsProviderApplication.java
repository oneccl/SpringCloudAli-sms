package com.cc.smsprovider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

// 开启Redis缓存
@EnableCaching
// 开启服务发现，注册到Nacos
@EnableDiscoveryClient
@MapperScan("com.cc.smsprovider.dao")
@SpringBootApplication
public class SmsProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmsProviderApplication.class, args);
    }

}
