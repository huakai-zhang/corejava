package com.spring.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spring.entity.User;
import org.apache.ibatis.cursor.Cursor;

import java.util.List;

public interface UserDS1Service extends IService<User> {
    List<User> getUserByCondition(Long id, String randomUser);

    Cursor<User> scan(int limit);
}
