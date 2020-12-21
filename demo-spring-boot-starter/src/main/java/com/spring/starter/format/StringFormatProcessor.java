package com.spring.starter.format;

import java.util.Objects;

/**
 * @author 春阳
 * @date 2020-12-21 17:23
 */
public class StringFormatProcessor implements FormatProcessor {
    @Override
    public <T> String format(T obj) {
        return "StringFormatProcessor:"+ Objects.toString(obj);
    }
}
