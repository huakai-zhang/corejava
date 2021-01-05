package com.spring.rpc.core.msg;

import java.io.Serializable;

public class InvokerMsg implements Serializable {

    // 服务名称
    private String className;

    // 调用方法
    private String methodName;

    // 参数列表
    private Class<?>[] parames;

    // 参数值
    private Object[] values;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParames() {
        return parames;
    }

    public void setParames(Class<?>[] parames) {
        this.parames = parames;
    }

    public Object[] getValues() {
        return values;
    }

    public void setValues(Object[] values) {
        this.values = values;
    }
}
