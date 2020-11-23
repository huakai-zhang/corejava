package com.spring.aop.test;

import com.spring.model.Blog;
import com.spring.mybatis.dao.BlogMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = {"classpath*:application-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class BlogMapperTest {

    @Autowired
    private BlogMapper mapper;

    @Test
    public void select() throws Exception {
        Blog blog = mapper.selectBlogPosts(1);
        System.out.println(blog.getName());
        Thread.sleep(5000);
        System.out.println(blog.getPosts().get(0).getBid());
    }

}
