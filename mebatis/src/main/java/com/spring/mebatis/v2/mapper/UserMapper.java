package com.spring.mebatis.v2.mapper;

import com.spring.mebatis.v2.annotation.Entity;
import com.spring.mebatis.v2.annotation.Select;

@Entity(User.class)
public interface UserMapper {

    @Select("select * from user where id = ?")
    public User selectUserById(Integer id);

}
