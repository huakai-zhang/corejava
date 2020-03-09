package com.spring.aop.service;

import com.spring.model.Member;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final static Logger logger = Logger.getLogger(AuthService.class);

    public Member login(String loginName, String loginPass) {
        logger.info("用户登陆");
        return null;
    }

    public boolean logout(String loginName) {
        logger.info("用户注销");
        return true;
    }
}
