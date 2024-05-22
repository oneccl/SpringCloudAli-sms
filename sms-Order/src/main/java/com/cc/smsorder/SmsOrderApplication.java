package com.cc.smsorder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

// 开启Redis缓存
@EnableCaching
// 开启服务调用组件Feign
@EnableFeignClients
// 开启服务发现，注册到Nacos
@EnableDiscoveryClient
// Mybatis扫描映射配置文件的Dao接口
@MapperScan("com.cc.smsorder.dao")
@SpringBootApplication
public class SmsOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmsOrderApplication.class, args);
    }

    @Bean
    // @LoadBalanced: 实现负载均衡；让RestTemplate对象认识服务ID
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
