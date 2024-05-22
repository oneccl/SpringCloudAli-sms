package com.cc.smsuser.dao;

import com.cc.smscommon.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: CC
 * E-mail: 203717588@qq.com
 * Date: 2023/1/3
 * Time: 19:24
 * Description:
 */
@Repository
public interface UserMapper {

    User queryCheck(User user);

    List<User> queryUsers(String name, Integer role);

    Integer delete(Integer id);

    Integer insert(User user);

    User queryById(Integer id);

    Integer update(User user);

    User queryByIdAndPw(Integer id,String pw);

    Integer updatePw(Integer id,String np);

}
