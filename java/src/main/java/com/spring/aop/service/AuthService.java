package com.spring.aop.service;

import com.spring.model.User;
import org.springframework.stereotype.Repository;

/**
 * @author 春阳
 * @date 2021-06-15 17:13
 */
public interface AuthService {
    User login(String loginName, String loginPass);

    boolean logout(String loginName);
}
