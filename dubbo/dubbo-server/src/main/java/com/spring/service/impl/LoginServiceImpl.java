package com.spring.service.impl;

import com.spring.service.LoginService;

/**
 * @author 春阳
 * @date 2021-01-22 12:10
 */
public class LoginServiceImpl implements LoginService {

    public String login(String username, String password) {
        if(username.equals("admin")&&password.equals("admin")){
            return "LOGIN SUCCESS!!!";
        }
        return "FAILED";
    }
}

