package com.cc.smsprovider.service.impl;

import com.cc.smscommon.pojo.Provider;
import com.cc.smsprovider.dao.ProviderMapper;
import com.cc.smsprovider.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: CC
 * E-mail: 203717588@qq.com
 * Date: 2023/1/6
 * Time: 8:55
 * Description:
 */
@Service("providerService")
public class ProviderServiceImpl implements ProviderService {

    @Autowired
    private ProviderMapper providerMapper;

    // @Cacheable：用于查询方法上，开启Redis缓存
    // Value：Redis缓存命名空间：providersQueryKeyList
    // Key：若不自定义Key生成器，则Key为@Cacheable注解所在的方法的所在全类名+方法名+参数值
    // Redis会将key及对应查询结果(Value)保存到缓存空间(providersQueryKeyList)
    @Cacheable(value = "providersQueryKeyList")
    @Override
    public List<Provider> findProviders(String code, String name) {
        return providerMapper.queryProviders(code,name);
    }

    @Override
    public Provider findById(Integer id) {
        return providerMapper.queryById(id);
    }

    @Override
    public Boolean delPro(Integer id) {
        return providerMapper.delete(id) > 0;
    }

    @Override
    public Boolean modifyPro(Provider provider) {
        return providerMapper.update(provider) > 0;
    }

    @Override
    public Boolean addPro(Provider provider) {
        return providerMapper.insert(provider) > 0;
    }

}
