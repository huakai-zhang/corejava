package com.spring.starter.format;

import com.alibaba.fastjson.JSON;

/**
 * @author 春阳
 * @date 2020-12-21 17:23
 */
public class JsonFormatProcessor implements FormatProcessor {
    @Override
    public <T> String format(T obj) {
        return "JsonFormatProcessor:"+ JSON.toJSONString(obj);
    }
}
