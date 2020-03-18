package com.mybatis.my.executor;

import com.spring.model.User;

import java.sql.*;

public class SimpleExecutor implements SpringExecutor {

    public <E> E query(String sql, Object parameter) {
        try {
            Connection conn = getConnection();
            PreparedStatement pstmt;
            pstmt = conn.prepareStatement(String.format(sql, Integer.parseInt(String.valueOf(parameter))));
            ResultSet rs = pstmt.executeQuery();
            User test = new User();
            while (rs.next()) {
                test.setId(rs.getInt(1));
                test.setName(rs.getString(2));
            }
            return (E) test;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Connection getConnection() throws SQLException {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/spring?useUnicode=true&characterEncoding=utf-8&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String username = "root";
        String password = "root";
        Connection conn = null;
        try {
            Class.forName(driver); //classLoader,加载对应驱动
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}