package com.mybatis.spring.config.mappers;

import com.spring.model.User;

public interface UserMapper {
    User selectByPrimaryKey(Integer userId);
}