package com.mybatis.spring.statement;

import com.mybatis.spring.config.Configuration;
import com.mybatis.spring.config.MapperRegistory;
import com.mybatis.spring.result.ResultSetHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StatementHandler {

    private final Configuration configuration;

    private final ResultSetHandler resultSetHandler;

    public StatementHandler(Configuration configuration) {
        this.configuration = configuration;
        this.resultSetHandler = new ResultSetHandler(configuration);
    }

    public <E> E query(MapperRegistory.MapperData mapperData, Object parameter) throws Exception {
        try {
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(String.format(mapperData.getSql(), Integer.parseInt(String.valueOf(parameter))));
            pstmt.execute();
            return (E)resultSetHandler.handle(pstmt, mapperData);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Connection getConnection() {
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
