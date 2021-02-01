package com.spring.design.proxy.javassist.dubbo.real;

/**
 * @author 春阳
 * @date 2021-01-29 14:37
 */
public class HuaKaiProtocol {

    public static  <T> AbstractHuaKaiProxyInvoker<T> getInvoker(Class<?> clazz) throws InstantiationException, IllegalAccessException {
        final HuaKaiWrapper wrapper = HuaKaiWrapper.getWrapper(clazz);

        return new AbstractHuaKaiProxyInvoker<T>() {
            @Override
            protected void doInvoke(String methodName) {
                wrapper.invokeMethod(methodName);
            }
        };
    }

    public static void main(String[] args) {
        try {
            AbstractHuaKaiProxyInvoker invoker = getInvoker(MyService.class);
            invoker.doInvoke("傻吊");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
