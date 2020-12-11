package com.spring.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spring.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.cursor.Cursor;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    List<User> getUserByAccount(@Param("randomUser") String randomUser);

    Cursor<User> scan(@Param("limit") int limit);
}
