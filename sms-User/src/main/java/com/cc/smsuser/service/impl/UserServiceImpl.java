package com.cc.smsuser.service.impl;

import com.cc.smscommon.pojo.User;
import com.cc.smsuser.dao.UserMapper;
import com.cc.smsuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: CC
 * E-mail: 203717588@qq.com
 * Date: 2023/1/3
 * Time: 19:26
 * Description:
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findById(Integer id) {
        return userMapper.queryById(id);
    }

    @Override
    public User check(User user) {
        return userMapper.queryCheck(user);
    }

    // @Cacheable：用于查询方法上，开启Redis缓存
    // Value：Redis缓存命名空间：usersQueryKeyList
    // Key：若不自定义Key生成器，则Key为@Cacheable注解所在的方法的所在全类名+方法名+参数值
    // Redis会将key及对应查询结果(Value)保存到缓存空间(usersQueryKeyList)
    @Cacheable(value = "usersQueryKeyList")
    @Override
    public List<User> findUsers(String name, Integer role) {
        return userMapper.queryUsers(name,role);
    }

    @Override
    public Boolean addUser(User user) {
        return userMapper.insert(user) > 0;
    }

    @Override
    public Boolean removeUser(Integer id) {
        return userMapper.delete(id) > 0;
    }

    @Override
    public Boolean updateUser(User user) {
        return userMapper.update(user) > 0;
    }

    @Override
    public User findByIdAndPw(Integer id, String op) {
        return userMapper.queryByIdAndPw(id,op);
    }

    @Override
    public Boolean updatePw(Integer id, String np) {
        return userMapper.updatePw(id,np) > 0;
    }

}
