package com.spring;

import java.io.Serializable;

/**
 * @author 花开不合阳春暮
 * @date 2021/1/1 下午12:31
 */
public class RpcRequest implements Serializable {

    private String className;
    private String methodName;
    private Object[] parameters;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }
}
