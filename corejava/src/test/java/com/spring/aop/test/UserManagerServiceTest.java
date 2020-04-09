package com.spring.aop.test;

import com.spring.aop.service.MemberManagerService;
import com.spring.model.User;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@ContextConfiguration(locations = {"classpath*:application-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserManagerServiceTest {

    @Autowired
    MemberManagerService memberManagerService;

    @Test
    @Ignore
    public void testAdd() throws Exception {
        boolean r = memberManagerService.add("Sam");
        System.out.println(r);
    }

    // 做事务代理的时候
    // TransactionManage来管理事务操作（切面）
    // DataSource,SessionFactory
    // DataSource包含了链接信息，事物的提交或回滚一些基础功能
    // 通过连接点是可以获得到方法（切点）具体操作哪个DataSource
    // 通过切面通知类型，去执行DataSource的功能方法
    @Test
    @Ignore
    public void testRemove() {
        try {
            memberManagerService.remove(0);
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    public void testModify() throws Exception {
        memberManagerService.modify(0, null);
    }

    @Test
    @Ignore
    public void testQuery() throws Exception {
        List<User> userList = memberManagerService.query();
        System.out.println(userList);
    }

    @Test
    //@Ignore
    public void login() throws Exception {
        memberManagerService.login(3, "CCC");
    }
}