package com.spring.dubbo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/5/8
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    JdbcTemplate userJdbcTemplate;

    @Override
    public void updateUser() {
        userJdbcTemplate.update
                ("update user set name='mic',mobile='13555555555',sex='male' where  id = 4");
    }
}
