package com.cc.smscommon.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Author: CC
 * E-mail: 203717588@qq.com
 * Date: 2023/1/6
 * Time: 11:15
 * Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {

    private Integer orderId;
    private String orderCode;
    private String goodsName;
    private String goodsDesc;
    private String goodsUnit;
    private Integer goodsCount;
    private Double totalPrice; // 商品总价
    private Integer isPayment;

    private String creationDate;
    private Integer providerId;

    private Provider provider;

    private Integer goodsId;

}
