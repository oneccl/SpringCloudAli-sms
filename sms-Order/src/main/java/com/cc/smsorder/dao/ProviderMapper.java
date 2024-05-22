package com.cc.smsorder.dao;

import com.cc.smscommon.pojo.Provider;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: CC
 * E-mail: 203717588@qq.com
 * Date: 2023/1/6
 * Time: 11:50
 * Description:
 */
@Repository
public interface ProviderMapper {

    List<Provider> queryAll();

}
