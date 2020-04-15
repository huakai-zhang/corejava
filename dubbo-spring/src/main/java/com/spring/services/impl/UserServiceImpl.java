package com.spring.services.impl;

import com.spring.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public void insert() {
        System.out.println("哈哈");
    }
}
