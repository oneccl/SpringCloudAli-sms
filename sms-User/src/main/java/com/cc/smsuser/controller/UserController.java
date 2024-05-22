package com.cc.smsuser.controller;

import com.cc.smscommon.pojo.User;
import com.cc.smscommon.utils.FileHandler;
import com.cc.smsuser.service.RoleService;
import com.cc.smsuser.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Author: CC
 * E-mail: 203717588@qq.com
 * Date: 2023/1/3
 * Time: 19:29
 * Description:
 */
//@CrossOrigin
@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    // 登录
    @RequestMapping("/login.do")
    public Object login(@RequestBody User user){
        User checkedUser = userService.check(user);
        if(checkedUser != null){
            return checkedUser;
        }
        return "用户名或密码不正确!";
    }

    // 查询
    @RequestMapping("/show.do")
    public Object findUsers(String name,Integer role, Integer pageNo){
        if (pageNo == null){
            pageNo = 1;
        }
        Map<String, Object> map = new HashMap<>();
        // 分页
        PageHelper.startPage(pageNo,5);
        List<User> users = userService.findUsers(name, role);
        PageInfo<User> page = new PageInfo<>(users);
        map.put("users",users);
        map.put("page",page);
        return map;
    }

    // 添加
    @RequestMapping("/add.do")
    public Object add(@RequestBody User user){
        return userService.addUser(user);
    }

    // 根据id查询
    @RequestMapping("/findById.do")
    public Object findById(Integer id){
        return userService.findById(id);
    }

    // 删除
    @RequestMapping("/remove.do")
    public Object remove(Integer id){
        return userService.removeUser(id);
    }

    // 修改
    @RequestMapping("/modify.do")
    public Object modify(@RequestBody User user){
        return userService.updateUser(user);
    }

    // 修改密码
    @RequestMapping("/modifyPw.do")
    public Object modifyPw(Integer id,String op,String np){
        return userService.findByIdAndPw(id,op)!=null?userService.updatePw(id,np):false;
    }

    /*  头像上传  */
    @RequestMapping("upload.do")
    public void upload(File file, HttpServletRequest req) throws Exception {
        DiskFileItemFactory factory = FileHandler.getDiskFileItemFactory(file);
        ServletFileUpload upload = FileHandler.getServletFileUpload(factory);
        List<FileItem> fileItems = FileHandler.parseRequest(upload, req);
        FileHandler.fiHandler(fileItems,req);
    }

}
