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
 * Time: 19:07
 * Description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private Integer userId;
    private String userCode;
    private String userName;
    private String userPassword;
    private Integer gender;
    private Integer age;
    private String birthday;
    private String phone;
    private String address;
    private Integer userRole;

    private Role role;

}
