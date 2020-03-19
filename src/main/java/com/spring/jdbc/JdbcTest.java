package com.spring.jdbc;

import com.alibaba.fastjson.JSON;
import com.spring.model.User;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcTest {
    // 框架做了两件事
    // 1，自动生成SQL语句
    // 2，自动ORM映射

    //Hibernate优点
    //1，API丰富，可以实现无SQL操作（HQL，为了兼容所有数据库，都会先解释为HQL，再由HQL翻译成SQL，当然有支持直接执行SQL的API，为了考虑用户需求的复杂性）
    //对所有数据库方言都支持的非常不错
    //2，ORM全自动化

    //Mybatis
    //1，轻量级，性能好
    //2，SQL和Java代码分离（SqlMap，把每一条SQL语句起一个名字作为Map的Key保存）
    //get("selectByName")

    //第一，性能更好，是啥就是啥，不经过二次处理
    //第二，单表操作实现NoSQL
    //第三，ORM零配置实现自动化
    //原则：约定优于配置（保证代码健壮性）
    //DAO原则：一个Dao只操作一张表
    //约定：做修改和删除的是根据主键来操作的
    //约定：读写分离
    //约定：支持分库分表
    //约定：ORM支持的类型原则上只认java八大基本数据类型+String（为了降低复杂度）
    //尽量使用单表操作，如果是在需要多表操作，可以把数据查出来放到内存，然后在内存中计算

    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        // 原生的JDBC如何操作？
        try {
            // 1,2步被封装成了DataSource，放入到了连接池
            // 目的是为了提高程序的响应速度
            // 1，加载驱动器
            Class.forName("com.mysql.jdbc.Driver");
            // 2，建立连接
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/spring", "root", "root");


            // 3，创建语句集
            PreparedStatement pstm = conn.prepareStatement("select * from user");
            // 4，执行
            ResultSet rs = pstm.executeQuery();


            // 第5步封装做成了ORM的过程
            // Object Relation Mapping，对象关系映射
            // 自动变成一个我们显而易见的自己写的Java对象（实体类）
            // 5，获取结果集
            int len = rs.getMetaData().getColumnCount();
            List<Object> result = new ArrayList<>();
            while (rs.next()) {
                Class clazz = User.class;
                Object obj = clazz.newInstance();
                for (int i = 1; i <= len; i++) {
                    String columnName = rs.getMetaData().getColumnName(i);
                    Field field = clazz.getDeclaredField(columnName);
                    // 前提条件是属性名和字段名一致（约定）
                    field.setAccessible(true);
                    Object type = field.getType();
                    if (type == int.class) {
                        field.set(obj, rs.getInt(columnName));
                    } else if (type == String.class) {
                        field.set(obj, rs.getString(columnName));
                    }
                }
                result.add(obj);
            }
            System.out.println(JSON.toJSON(result));
            rs.close();
            pstm.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            conn.rollback();
        }
    }

}
