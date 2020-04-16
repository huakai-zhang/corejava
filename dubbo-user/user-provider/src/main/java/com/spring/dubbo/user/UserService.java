package com.spring.dubbo.user;

import org.springframework.stereotype.Service;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/4/16
 */
@Service(value = "userService")
public class UserService implements IUserService {
    @Override
    public UserResponse login(String username, String password) {
        System.out.println("帐号为：" + username + ",密码为：" + password);
        UserResponse response = new UserResponse();
        response.setCode("11111");
        response.setMsg("登录成功");
        return response;
    }

    @Override
    public void toLogin(String name) {
        System.out.println("来自：" + name + "，等待登录调用。");
    }
}
