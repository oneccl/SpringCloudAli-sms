package com.cc.smsorder.service.impl;

import com.cc.smscommon.pojo.Provider;
import com.cc.smsorder.dao.ProviderMapper;
import com.cc.smsorder.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: CC
 * E-mail: 203717588@qq.com
 * Date: 2023/1/6
 * Time: 16:48
 * Description:
 */
@Service("providerService")
public class ProviderServiceImpl implements ProviderService {

    @Autowired
    private ProviderMapper providerMapper;

    @Override
    public List<Provider> findAll() {
        return providerMapper.queryAll();
    }

}
