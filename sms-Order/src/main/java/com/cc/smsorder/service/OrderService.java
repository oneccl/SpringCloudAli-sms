package com.cc.smsorder.service;

import com.cc.smscommon.pojo.Order;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: CC
 * E-mail: 203717588@qq.com
 * Date: 2023/1/6
 * Time: 11:22
 * Description:
 */
public interface OrderService {

    List<Order> findOrders(String name, Integer proId, Integer isPay);

    Order findById(Integer id);

    Boolean delOrder(Integer id);

    Boolean modOrder(Order order);

    Boolean addOrder(Order order);

}
