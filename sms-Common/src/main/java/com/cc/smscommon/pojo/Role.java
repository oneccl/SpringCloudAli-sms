package com.cc.smscommon.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Author: CC
 * E-mail: 203717588@qq.com
 * Date: 2023/1/3
 * Time: 19:09
 * Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {

    private Integer roleId;
    private String roleCode;
    private String roleName;

}
