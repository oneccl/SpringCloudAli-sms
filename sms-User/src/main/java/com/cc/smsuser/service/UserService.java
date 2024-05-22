package com.cc.smsuser.service;

import com.cc.smscommon.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: CC
 * E-mail: 203717588@qq.com
 * Date: 2023/1/3
 * Time: 19:26
 * Description:
 */
public interface UserService {

    User findById(Integer id);

    User check(User user);

    List<User> findUsers(String name, Integer role);

    Boolean addUser(User user);

    Boolean removeUser(Integer id);

    Boolean updateUser(User user);

    User findByIdAndPw(Integer id, String op);

    Boolean updatePw(Integer id, String np);

}
