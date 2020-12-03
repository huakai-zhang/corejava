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
public class MybatisTest {

    public static void main(String[] args) throws IOException {
        // 使用类加载器，加载mybatis的配置文件
        InputStream inputStream= Resources.getResourceAsStream("mybatis-config.xml");

        // 构件sqlSession工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //SqlSession sqlSession1 = sqlSessionFactory.openSession();

        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.selectById(1);
            System.out.println(user);
            User user1 = new User();
            user1.setName("A");
            userMapper.insertUser(user1);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
        //sqlSession.close();
        /*UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);
        List<User> users1 = userMapper1.selectAll();
        System.out.println(users1);
        sqlSession.close();
        sqlSession1.close();*/
    }
}
