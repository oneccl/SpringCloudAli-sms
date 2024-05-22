package com.cc.smsprovider.service;

import com.cc.smscommon.pojo.Provider;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: CC
 * E-mail: 203717588@qq.com
 * Date: 2023/1/6
 * Time: 8:55
 * Description:
 */
public interface ProviderService {

    List<Provider> findProviders(String code, String name);

    Provider findById(Integer id);

    Boolean delPro(Integer id);

    Boolean modifyPro(Provider provider);

    Boolean addPro(Provider provider);

}
