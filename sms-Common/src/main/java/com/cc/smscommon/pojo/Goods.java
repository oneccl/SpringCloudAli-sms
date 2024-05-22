package com.cc.smscommon.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Author: CC
 * E-mail: 203717588@qq.com
 * Date: 2023/1/12
 * Time: 9:19
 * Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods implements Serializable {

    private Integer goodsId;
    private String goodsName;
    private String goodsDesc;
    private String goodsUnit; // 商品单位
    private Double goodsCount; // 商品已选数量
    private String goodsSort; // 商品种类
    private Double goodsPrice; // 单价
    private Integer goodsStock; // 库存

}
