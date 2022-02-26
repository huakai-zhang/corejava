package com.spring.aop.test;

import com.spring.model.User;
import com.spring.mybatis.dao.TKUserMapper;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author 春阳
 * @date 2020-12-10 10:57
 */
@ContextConfiguration(locations = {"classpath*:application-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TKUserMapperTest {

    @Autowired
    private TKUserMapper userMapper;

    @Test
    public void select() {
        System.out.println(userMapper.selectAll());
    }

}
