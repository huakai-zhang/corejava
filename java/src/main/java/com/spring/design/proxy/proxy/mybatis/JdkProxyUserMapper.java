package com.spring.design.proxy.proxy.mybatis;

import com.spring.model.User;

import java.util.List;

/**
 * @author 春阳
 * @date 2020-12-11 11:00
 */
public interface JdkProxyUserMapper {

    List<User> selectAll();

    default String select() {
        System.out.println("default");
        return "default";
    }

}
