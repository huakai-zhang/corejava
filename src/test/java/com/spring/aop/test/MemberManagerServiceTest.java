package com.spring.aop.test;

import com.spring.aop.service.MemberManagerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = {"classpath*:application-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class MemberManagerServiceTest {

    @Autowired
    MemberManagerService memberManagerService;

    @Test
    public void testAdd() {
        memberManagerService.add(null);
    }

    public void testRemove() {
        try {
            memberManagerService.remove(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testModify() {
        memberManagerService.modify(null);
    }

    public void testQuery() {
        memberManagerService.query(null);
    }

}
