package com.cc.smsorder.service.impl;

import com.cc.smscommon.pojo.Order;
import com.cc.smsorder.dao.OrderMapper;
import com.cc.smsorder.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: CC
 * E-mail: 203717588@qq.com
 * Date: 2023/1/6
 * Time: 11:23
 * Description:
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    // @Cacheable：用于查询方法上，开启Redis缓存
    // Value：Redis缓存命名空间：ordersQueryKeyList
    // Key：若不自定义Key生成器，则Key为@Cacheable注解所在的方法的所在全类名+方法名+参数值
    // Redis会将key及对应查询结果(Value)保存到缓存空间(ordersQueryKeyList)
    @Cacheable(value = "ordersQueryKeyList")
    @Override
    public List<Order> findOrders(String name, Integer proId, Integer isPay) {
        return orderMapper.queryOrders(name,proId,isPay);
    }

    @Override
    public Order findById(Integer id) {
        return orderMapper.queryById(id);
    }

    @Override
    public Boolean delOrder(Integer id) {
        return orderMapper.delete(id) > 0;
    }

    @Override
    public Boolean modOrder(Order order) {
        return orderMapper.update(order) > 0;
    }

    @Override
    public Boolean addOrder(Order order) {
        return orderMapper.insert(order) > 0;
    }

}
