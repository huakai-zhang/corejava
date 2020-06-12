package com.spring.dubbo.user;

import com.spring.dubbo.user.dto.DebitRequest;
import com.spring.dubbo.user.dto.DebitResponse;
import com.spring.dubbo.user.dto.UserRequest;
import com.spring.dubbo.user.dto.UserResponse;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/4/16
 */
public interface IUserService {
    UserResponse login(String username, String password);
    void toLogin(String name);

    DebitResponse debit(DebitRequest request);

    UserResponse checkAuth(UserRequest request);
}
