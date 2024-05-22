package com.cc.smsorder.controller;

import com.cc.smscommon.pojo.Goods;
import com.cc.smscommon.pojo.Order;
import com.cc.smscommon.pojo.Provider;
import com.cc.smsorder.service.GoodsServiceFeign;
import com.cc.smsorder.service.OrderService;
import com.cc.smsorder.service.ProviderService;
import com.cc.smsorder.service.ProviderServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created with IntelliJ IDEA.
 * Author: CC
 * E-mail: 203717588@qq.com
 * Date: 2023/1/6
 * Time: 11:24
 * Description:
 */
//@CrossOrigin
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ProviderService providerService;

    // RestTemplate是Spring框架提供的基于REST的服务组件
    @Autowired
    private RestTemplate restTemplate;
    // Feign服务调用：调用Goods-Service服务
    @Autowired
    private GoodsServiceFeign goodsServiceFeign;
    // Feign服务调用：调用Provider-Service服务
    @Autowired
    private ProviderServiceFeign providerServiceFeign;

    // 查询
    @RequestMapping("/getOrders.do")
    public Object getOrders(String name,Integer proId,Integer isPay){
        Map<String, Object> map = new HashMap<>();

        // 方式1：本服务中重新写一个Provider的查询
        //List<Provider> providerList = providerService.findAll();

        // 方式2：Feign服务调用：调用Provider-Service服务中getProviders()方法
        List<Provider> providerList = providerServiceFeign.getProviders();

        List<Order> orderList = orderService.findOrders(name, proId, isPay);
        map.put("providers",providerList);
        map.put("orders",orderList);
        return map;
    }

    // 根据id查询
    @RequestMapping("/findById.do")
    public Object findById(Integer id){
        return orderService.findById(id);
    }

    // 删除
    @RequestMapping("/remove.do")
    public Object remove(Integer id){
        return orderService.delOrder(id);
    }

    // 修改
    @RequestMapping("/modify.do")
    public Object modify(@RequestBody Order order){
        return orderService.modOrder(order);
    }

    // 添加
    @RequestMapping("/add.do")
    public Object add(@RequestBody Order order){
        SimpleDateFormat T = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String timeNow = T.format(new Date());
        order.setCreationDate(timeNow);
        return orderService.addOrder(order);
    }

    /*   服务调用   */

    // 服务调用：Order服务调用Goods服务
    @RequestMapping("/serviceInvoke.do")
    public Object invokeGoodsService(Integer id) throws Exception {
        // 方式1：使用RestTemplate服务调用
//        Goods goods1 = restTemplate.getForObject(new URI("http://Goods-Service/findById.do/"+id), Goods.class);
//        System.out.println("RestTemplate服务调用: "+goods1);
//        return goods1;

        // 方式2：使用Feign服务调用
        Goods goods2 = goodsServiceFeign.findById(id);
        System.out.println("Feign服务调用: "+goods2);
        return goods2;
    }

}
