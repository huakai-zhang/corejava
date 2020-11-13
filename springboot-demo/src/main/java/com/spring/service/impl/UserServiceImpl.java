package com.spring.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.entity.User;
import com.spring.mapper.UserMapper;
import com.spring.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public List<User> getUserByAccount(String randomUser) {
        return baseMapper.getUserByAccount(randomUser);
    }
}
