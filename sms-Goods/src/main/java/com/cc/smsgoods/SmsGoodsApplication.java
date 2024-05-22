package com.cc.smsgoods;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

// 开启服务发现，注册到Nacos
@EnableDiscoveryClient
// Mybatis扫描映射配置文件的Dao接口
@MapperScan("com.cc.smsgoods.dao")
@SpringBootApplication
public class SmsGoodsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmsGoodsApplication.class, args);
    }

}
