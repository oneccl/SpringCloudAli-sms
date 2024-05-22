package com.cc.smsorder.service;

import com.cc.smscommon.pojo.Goods;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * Author: CC
 * E-mail: 203717588@qq.com
 * Date: 2023/1/12
 * Time: 10:30
 * Description:
 */
// Feign服务调用：订单服务Order-Service调用商品服务Goods-Service
// 指定要调用的服务
@FeignClient("Goods-Service")
public interface GoodsServiceFeign {

    // 指定要调用的方法
    @RequestMapping("/findById.do/{pId}")
    Goods findById(@PathVariable("pId") Integer id);

    // 完整路径：Goods-Service/findById.do/参数

}
