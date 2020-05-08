package com.spring.dubbo.user;

import com.spring.dubbo.dao.UserDao;
import com.spring.dubbo.user.dto.DebitRequest;
import com.spring.dubbo.user.dto.DebitResponse;
import com.spring.dubbo.user.dto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/4/16
 */
@Service(value = "userService")
public class UserService implements IUserService {

    @Autowired
    UserDao userDao;

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

    @Override
    public DebitResponse debit(DebitRequest request) {
        DebitResponse response = new DebitResponse();
        userDao.updateUser();
        response.setCode("000000");
        response.setMemo("成功");
        return response;
    }
}
