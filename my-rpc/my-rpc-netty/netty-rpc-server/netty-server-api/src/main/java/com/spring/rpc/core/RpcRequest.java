package com.spring.rpc.core;

import java.io.Serializable;

public class RpcRequest implements Serializable {

    /**
     * 服务名
     */
    private String className;
    /**
     * 方法名
     */
    private String methodName;
    /**
     * 实参列表
     */
    private Object[] parameters;
    /**
     * 形参列表
     */
    private Class<?>[] parameterTypes;
    private String version;

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

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
