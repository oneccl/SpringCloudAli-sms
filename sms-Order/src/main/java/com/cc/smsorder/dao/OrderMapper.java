package com.cc.smsorder.dao;

import com.cc.smscommon.pojo.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: CC
 * E-mail: 203717588@qq.com
 * Date: 2023/1/6
 * Time: 11:21
 * Description:
 */
@Repository
public interface OrderMapper {

    List<Order> queryOrders(String name, Integer proId, Integer isPay);

    Order queryById(Integer id);

    Integer delete(Integer id);

    Integer update(Order order);

    Integer insert(Order order);

}
