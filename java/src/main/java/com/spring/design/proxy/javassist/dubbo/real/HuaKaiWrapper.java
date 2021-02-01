package com.spring.design.proxy.javassist.dubbo.real;

import java.lang.reflect.Modifier;

/**
 * @author 春阳
 * @date 2021-01-29 14:11
 */
public abstract class HuaKaiWrapper {
    public static HuaKaiWrapper getWrapper(Class<?> c) throws IllegalAccessException, InstantiationException {
        ClassLoader cl = HuaKaiLoader.getClassLoader(c);
        StringBuilder c1 = new StringBuilder("public void invokeMethod(String s) { System.out.println(s); }");
        HuaKaiGenerator cc = HuaKaiGenerator.newInstance(cl);
        cc.setClassName((Modifier.isPublic(c.getModifiers()) ? HuaKaiWrapper.class.getName() : c.getName() + "$sw") + 0);
        cc.addMethod(c1.toString());
        Class<?> wc = cc.toClass();
        return (HuaKaiWrapper) wc.newInstance();
    }

    abstract public void invokeMethod(String mn);

}
