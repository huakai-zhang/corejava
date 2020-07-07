package com.spring.test;

import com.alibaba.fastjson.JSON;
import com.spring.demo.dao.MemberDao;
import com.spring.demo.entity.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/7/7
 */
@ContextConfiguration(locations={"classpath*:application-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class MemberTest {

    @Autowired
    MemberDao memberDao;

    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    public void testFind() {
        /*Member member = new Member();
        member.setName("菲菲");
        member.setAge(1);
        mongoTemplate.insert(member);*/

        /*Query query = new Query();
        List<Member> result = mongoTemplate.find(query, Member.class);*/

        List<Member> result = memberDao.getAll();
        System.out.println(JSON.toJSONString(result, true));
    }

}
