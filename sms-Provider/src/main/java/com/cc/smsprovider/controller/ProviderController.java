package com.cc.smsprovider.controller;

import com.cc.smscommon.pojo.Provider;
import com.cc.smsprovider.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Author: CC
 * E-mail: 203717588@qq.com
 * Date: 2023/1/6
 * Time: 8:56
 * Description:
 */
//@CrossOrigin
@RestController
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    // 查询
    @RequestMapping("/getProviders.do")
    public Object getProviders(String code,String name){
        return providerService.findProviders(code, name);
    }

    // 根据id查询
    @RequestMapping("/findById.do")
    public Object findById(Integer id){
        return providerService.findById(id);
    }

    // 删除
    @RequestMapping("/remove.do")
    public Object remove(Integer id){
        return providerService.delPro(id);
    }

    // 修改
    @RequestMapping("/modify.do")
    public Object modify(@RequestBody Provider provider){
        return providerService.modifyPro(provider);
    }

    // 添加
    @RequestMapping("/add.do")
    public Object add(@RequestBody Provider provider){
        SimpleDateFormat T = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String timeNow = T.format(new Date());
        provider.setCreationDate(timeNow);
        return providerService.addPro(provider);
    }

}
