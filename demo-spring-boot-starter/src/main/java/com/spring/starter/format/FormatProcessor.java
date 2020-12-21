package com.spring.starter.format;

/**
 * @author 春阳
 * @date 2020-12-21 17:23
 */
public interface FormatProcessor {
    public <T> String format(T obj);
}
