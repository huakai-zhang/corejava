package com.spring.mybatis.dao;

import com.spring.model.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    List<User> selectAll();

    User selectById(Integer Id);

    User selectByIds(User user);

//    @Select("select * from user")
//    List<User> selectAll();

    void insertUser(User user);

    void updateUser(User user);
}