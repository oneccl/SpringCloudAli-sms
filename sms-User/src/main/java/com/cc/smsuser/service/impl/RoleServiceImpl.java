package com.cc.smsuser.service.impl;

import com.cc.smscommon.pojo.Role;
import com.cc.smsuser.dao.RoleMapper;
import com.cc.smsuser.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: CC
 * E-mail: 203717588@qq.com
 * Date: 2023/1/3
 * Time: 19:37
 * Description:
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findAll() {
        return roleMapper.queryAll();
    }

}
