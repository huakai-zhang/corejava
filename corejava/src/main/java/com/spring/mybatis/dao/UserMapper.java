package com.spring.mybatis.dao;

import com.spring.model.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    List<User> selectAll();

    User selectById(Integer Id);

//    @Select("select * from user")
//    List<User> selectAll();

    void insertUser(User user);

}