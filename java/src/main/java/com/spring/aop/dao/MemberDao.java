package com.spring.aop.dao;

import com.spring.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

//@Repository
public class MemberDao {

    private JdbcTemplate template;

    @Resource(name="dataSource")
    public void setDataSource(DataSource dataSource){
        template = new JdbcTemplate(dataSource);
    }


    public List<User> select(){

        return template.query("select * from user", new RowMapper(){

            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User m = new User();
                m.setId(rs.getInt("id"));
                m.setName(rs.getString("name"));
                return m;
            }

        });

    }


    public int insert(String name) throws Exception{
        return template.update("insert into user(name) values(?)",name);
    }


    public int delete(long id) throws Exception{
        return template.update("delete from user where id = ?",id);
    }


    public int update(long id,String name) throws Exception{
        return template.update("update user set name = ? where id = ?",name,id);
    }
}
