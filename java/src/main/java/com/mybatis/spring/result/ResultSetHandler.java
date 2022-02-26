package com.mybatis.spring.result;

import com.mybatis.spring.config.Configuration;
import com.mybatis.spring.config.MapperRegistory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetHandler {

    private final Configuration configuration;

    public ResultSetHandler(Configuration configuration) {
        this.configuration = configuration;
    }

    public <E> E handle(PreparedStatement pstmt, MapperRegistory.MapperData mapperData) throws Exception {
        Object resultObj = new DefaultObjectFactory().create(mapperData.getType());
        ResultSet rs = pstmt.getResultSet();
        if (rs.next()) {
            int i = 0;
            for (Field field : resultObj.getClass().getDeclaredFields()) {
                setValue(resultObj, field, rs, i);
            }
        }
        return (E) resultObj;
    }

    private void setValue(Object resultObj, Field field, ResultSet rs, int i) throws Exception {
        Method setMethod = resultObj.getClass().getMethod("set" + upperCapital(field.getName()), field.getType());
        Object object = getResult(field, rs);
        setMethod.invoke(resultObj, object);
    }

    private Object getResult(Field field, ResultSet rs) throws SQLException {
        Class<?> type = field.getType();
        if (Integer.class == type || int.class == type) {
            return rs.getInt(field.getName());
        }
        return rs.getString(field.getName());
    }

    private String upperCapital(String name) {
        String first = name.substring(0, 1);
        String tail = name.substring(1);
        return first.toUpperCase() + tail;
    }
}
