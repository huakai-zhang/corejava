package com.spring.aop.service;

import com.spring.model.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final static Logger logger = Logger.getLogger(AuthServiceImpl.class);

    @Override
    public User login(String loginName, String loginPass) {
        System.out.println("用户登陆");
        logger.info("用户登陆");
        return null;
    }

    @Override
    public boolean logout(String loginName) {
        logger.info("用户注销");
        return true;
    }
}
