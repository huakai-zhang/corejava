package com.spring.service.impl;

//import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.entity.User;
import com.spring.mapper.UserMapper;
import com.spring.service.MemberService;
import com.spring.service.UserDS1Service;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cursor.Cursor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
//@DS("db2")
public class UserDS1ServiceImpl extends ServiceImpl<UserMapper, User> implements UserDS1Service {

    @Autowired
    private MemberService memberService;

    @Override
    public List<User> getUserByCondition(Long id, String randomUser) {
        return baseMapper.getUserByCondition(id, randomUser);
    }

    @Override
    public Cursor<User> scan(int limit) {
        return baseMapper.scan(limit);
    }
}
