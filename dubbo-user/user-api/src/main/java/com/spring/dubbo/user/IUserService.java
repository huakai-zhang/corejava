package com.spring.dubbo.user;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/4/16
 */
public interface IUserService {
    UserResponse login(String username, String password);
    void toLogin(String name);
}
