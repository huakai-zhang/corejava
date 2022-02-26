package com.mybatis.my.mapper;

import com.spring.model.User;

public interface UserMapper {
    User selectByPrimaryKey(Integer userId);
}