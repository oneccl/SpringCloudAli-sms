package com.cc.smsgoods.service.impl;

import com.cc.smscommon.pojo.Goods;
import com.cc.smsgoods.dao.GoodsMapper;
import com.cc.smsgoods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * Author: CC
 * E-mail: 203717588@qq.com
 * Date: 2023/1/12
 * Time: 10:16
 * Description:
 */
@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    public Goods findById(Integer id){
        return goodsMapper.queryById(id);
    }

}
