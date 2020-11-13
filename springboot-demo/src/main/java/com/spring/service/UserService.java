package com.spring.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spring.entity.User;

import java.util.List;

public interface UserService extends IService<User> {
    List<User> getUserByAccount(String randomUser);
}
