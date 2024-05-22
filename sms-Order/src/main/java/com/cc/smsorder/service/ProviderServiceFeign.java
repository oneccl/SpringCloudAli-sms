package com.cc.smsorder.service;

import com.cc.smscommon.pojo.Provider;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: CC
 * E-mail: 203717588@qq.com
 * Date: 2023/1/12
 * Time: 13:31
 * Description:
 */
// Feign服务调用：订单服务Order-Service调用商家服务Provider-Service
// 指定要调用的服务
@FeignClient("Provider-Service")
public interface ProviderServiceFeign {

    // 指定要调用的方法
    @RequestMapping("/getProviders.do")
    List<Provider> getProviders();

    // 完整路径：Provider-Service/getProviders.do/参数

}
