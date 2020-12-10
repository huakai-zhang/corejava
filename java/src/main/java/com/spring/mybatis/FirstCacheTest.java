package com.spring.mybatis;

import com.spring.model.User;
import com.spring.mybatis.dao.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Spring Zhang
 * @date 2019/12/16 13:20
 */
public class FirstCacheTest {

    public static void main(String[] args) throws IOException {
        InputStream inputStream= Resources.getResourceAsStream("mybatis-config.xml");

        // 构件sqlSession工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            System.out.println(userMapper.selectAll());
            sqlSession.commit();

            UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);
            User user = new User();
            user.setId(15);
            user.setName("E");
            userMapper1.updateUser(user);
            sqlSession1.commit();

            //UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
            System.out.println(userMapper.selectAll());
            sqlSession.commit();

            sqlSession.close();
            sqlSession1.close();
            /*User user = userMapper.selectById(1);
            System.out.println(user);
            User user1 = new User();
            user1.setName("A");
            userMapper.insertUser(user1);
            sqlSession.commit();*/
        } finally {
            sqlSession.close();
        }
    }
}
