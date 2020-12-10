package com.spring.mybatis.sessiontemplate.impl;

import com.spring.model.User;
import com.spring.mybatis.dao.UserMapper;
import com.spring.mybatis.sessiontemplate.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 春阳
 * @date 2020-12-09 16:03
 */
@Repository
public class UserTemplateDaoImpl extends BaseDao implements UserMapper {
    @Override
    public List<User> selectAll() {
        return null;
    }

    @Override
    public User selectById(Integer Id) {
        User emp = (User)
                this.selectOne("com.spring.mybatis.dao.UserMapper.selectById", Id);
        return emp;
    }

    @Override
    public void insertUser(User user) {

    }

    @Override
    public void updateUser(User user) {

    }
}
