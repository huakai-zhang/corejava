package com.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 春阳
 * @date 2021-01-14 18:36
 */
@ConfigurationProperties("zuul.filter")
public class FilterConfiguration {

    private String root;
    private Integer interval;

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }
}
