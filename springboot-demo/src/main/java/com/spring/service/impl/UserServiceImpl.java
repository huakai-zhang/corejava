package com.spring.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.config.datasource.MultipleDB;
import com.spring.entity.User;
import com.spring.mapper.UserMapper;
import com.spring.service.MemberService;
import com.spring.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cursor.Cursor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private MemberService memberService;

    @Override
    //@MultipleDB
    public List<User> getUserByCondition(Long channelId, Long id, String randomUser) {
        return baseMapper.getUserByCondition(id, randomUser);
    }

    @Override
    public Cursor<User> scan(int limit) {
        return baseMapper.scan(limit);
    }

    @Override
    @Async
    public void asyncThread() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("我执行完了");
        //return Math.random();
    }
}
