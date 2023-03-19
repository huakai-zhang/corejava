package com.spring.config.datasource;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class HikariProperties {
    public HikariDataSource dataSource(HikariDataSource dataSource) {
        // 连接池允许的最大连接数
        dataSource.setMaximumPoolSize(10);
        return dataSource;
    }
}
