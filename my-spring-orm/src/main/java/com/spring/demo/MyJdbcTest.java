package com.spring.demo;

import com.spring.demo.entity.Member;
import com.spring.demo.entity.User;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

/**
 * @author 春阳
 * @date 2020-12-09 11:36
 */
public class MyJdbcTest {
    public static void main(String[] args) {
        Member condition = new Member();
        //condition.setName("XiaoXiao");
        //condition.setAge(18);
        List<?> result =  select(condition);
        System.out.println(Arrays.toString(result.toArray()));
    }

    public static List<?> select(Object condition) {
        List<Object> result = new ArrayList<>();

        Class<?> entityClass = condition.getClass();

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            //1、加载驱动类
            Class.forName("com.mysql.jdbc.Driver");
            //2、建立连接
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/spring", "root", "root");

            //根据列名找属性名
            Map<String, String> columnMapper = new HashMap<>();
            //根据属性名找字段名
            Map<String, String> fieldMapper = new HashMap<>();

            Field[] fields = entityClass.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                if (field.isAnnotationPresent(Column.class)) {
                    Column column = field.getAnnotation(Column.class);
                    String columnName = column.name();
                    columnMapper.put(columnName, fieldName);
                    fieldMapper.put(fieldName, columnName);
                } else {
                    columnMapper.put(fieldName, fieldName);
                    fieldMapper.put(fieldName, fieldName);
                }
            }

            //3、创建语句集
            Table table = entityClass.getAnnotation(Table.class);
            String sql = "select * from " + table.name();

            StringBuilder where = new StringBuilder(" where 1 = 1");
            for (Field field : fields) {
                Object value =field.get(condition);
                if(null != value){
                    if(String.class == field.getType()) {
                        where.append(" and " + fieldMapper.get(field.getName()) + " = '" + value + "'");
                    }else{
                        where.append(" and " + fieldMapper.get(field.getName()) + " = " + value + "");
                    }
                    //其他的，在这里就不一一列举，下半截我们手写 ORM 框架会完善
                }
            }
            System.out.println(sql + where.toString());
            pstm = conn.prepareStatement(sql + where.toString());
            //4、执行语句集
            rs = pstm.executeQuery();
            //保存了处理真正数值以外的所有的附加信息
            int columnCounts = rs.getMetaData().getColumnCount();
            //5、获取结果集
            while (rs.next()){
                Object instance = entityClass.newInstance();
                for (int i = 1; i <= columnCounts; i++) {
                    //实体类 属性名，对应数据库表的字段名
                    //可以通过反射机制拿到实体类的说有的字段
                    //从 rs 中取得当前这个游标下的类名
                    String columnName = rs.getMetaData().getColumnName(i);
                    //有可能是私有的
                    Field field = entityClass.getDeclaredField(columnMapper.get(columnName));
                    field.setAccessible(true);
                    field.set(instance,rs.getObject(columnName));
                }
                result.add(instance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //6、关闭结果集、关闭语句集、关闭连接
            try {
                rs.close();
                pstm.close();
                conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return result;
    }
}
