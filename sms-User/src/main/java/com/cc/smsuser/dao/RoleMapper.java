package com.cc.smsuser.dao;

import com.cc.smscommon.pojo.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: CC
 * E-mail: 203717588@qq.com
 * Date: 2023/1/3
 * Time: 19:33
 * Description:
 */
@Repository
public interface RoleMapper {

    List<Role> queryAll();

}
