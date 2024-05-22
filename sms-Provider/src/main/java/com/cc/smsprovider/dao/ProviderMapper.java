package com.cc.smsprovider.dao;

import com.cc.smscommon.pojo.Provider;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: CC
 * E-mail: 203717588@qq.com
 * Date: 2023/1/6
 * Time: 8:51
 * Description:
 */
@Repository
public interface ProviderMapper {

    List<Provider> queryProviders(String code, String name);

    Provider queryById(Integer id);

    Integer delete(Integer id);

    Integer update(Provider provider);

    Integer insert(Provider provider);

}
