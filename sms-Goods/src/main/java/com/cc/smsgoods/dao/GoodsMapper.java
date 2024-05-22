package com.cc.smsgoods.dao;

import com.cc.smscommon.pojo.Goods;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * Author: CC
 * E-mail: 203717588@qq.com
 * Date: 2023/1/12
 * Time: 9:17
 * Description:
 */
@Repository
public interface GoodsMapper{

    Goods queryById(Integer id);

}
