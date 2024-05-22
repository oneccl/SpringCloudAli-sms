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
 * Time: 8:45
 * Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Provider implements Serializable {

    private Integer proId;
    private String proCode;
    private String proName;
    private String proDesc;
    private String proContact;
    private String proPhone;
    private String proAddress; // 供应商地址
    private String proFax;

    private String creationDate;

}
