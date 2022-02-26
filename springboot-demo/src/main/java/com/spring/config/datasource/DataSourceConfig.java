package com.spring.config.datasource;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    @Bean(name = "dataSourceDb1")
    @ConfigurationProperties("spring.datasource.db1")
    public DataSource dataSourceDb1(HikariProperties properties) {
        // 创建数据源
        HikariDataSource dataSource = DataSourceBuilder.create().type(HikariDataSource.class).build();
        // 数据源配置
        return properties.dataSource(dataSource);
    }

    @Bean(name = "dataSourceDb2")
    @ConfigurationProperties("spring.datasource.db2")
    @ConditionalOnProperty(prefix = "spring.datasource.db2", name = "enabled", havingValue = "true")
    public DataSource dataSourceDb2(HikariProperties properties) {
        // 创建数据源
        HikariDataSource dataSource = DataSourceBuilder.create().type(HikariDataSource.class).build();
        // 数据源配置
        return properties.dataSource(dataSource);
    }

    @Bean(name = "dynamicDataSource")
    @Primary
    public DynamicDataSource dataSource(DataSource dataSourceDb1, DataSource dataSourceDb2) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceType.db1.name(), dataSourceDb1);
        targetDataSources.put(DataSourceType.db2.name(), dataSourceDb2);
        return new DynamicDataSource(dataSourceDb1, targetDataSources);
    }
}
