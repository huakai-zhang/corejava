package com.mybatis.spring.config;

import java.io.IOException;

public class Configuration {

    private String scanPath;

    private MapperRegistory mapperRegistory = new MapperRegistory();

    public Configuration scanPath(String scanPath) {
        this.scanPath = scanPath;
        return this;
    }

    public MapperRegistory getMapperRegistory() {
        return mapperRegistory;
    }

    public void build() throws IOException {
        if (null == scanPath || scanPath.length() < 1) {
            throw new RuntimeException("scan path is required .");
        }
    }

    public void setScanPath(String scanPath) {
        this.scanPath = scanPath;
    }
}
