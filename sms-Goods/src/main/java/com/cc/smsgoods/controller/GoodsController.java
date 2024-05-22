package com.cc.smsgoods.controller;

import com.cc.smsgoods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * Author: CC
 * E-mail: 203717588@qq.com
 * Date: 2023/1/12
 * Time: 10:20
 * Description:
 */
@RestController
public class GoodsController {

    @Autowired
    private GoodsService goodsService;
    @Value("${server.port}")
    private Integer port;

    @RequestMapping("/findById.do/{pId}")
    public Object findById(@PathVariable("pId") Integer id){
        System.out.println("Goods-Service服务端口: "+port);
        return goodsService.findById(id);
    }

}
