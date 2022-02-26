package com.spring.design.proxy.javassist.dubbo.real;

import javassist.*;

import java.lang.reflect.Method;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 春阳
 * @date 2021-01-29 15:15
 */
public class HuaKaiGenerator {
    /**
     * void(V).
     */
    public static final char JVM_VOID = 'V';

    /**
     * boolean(Z).
     */
    public static final char JVM_BOOLEAN = 'Z';

    /**
     * byte(B).
     */
    public static final char JVM_BYTE = 'B';

    /**
     * char(C).
     */
    public static final char JVM_CHAR = 'C';

    /**
     * double(D).
     */
    public static final char JVM_DOUBLE = 'D';

    /**
     * float(F).
     */
    public static final char JVM_FLOAT = 'F';

    /**
     * int(I).
     */
    public static final char JVM_INT = 'I';

    /**
     * long(J).
     */
    public static final char JVM_LONG = 'J';

    /**
     * short(S).
     */
    public static final char JVM_SHORT = 'S';
    private static final Map<ClassLoader, ClassPool> POOL_MAP = new ConcurrentHashMap<ClassLoader, ClassPool>();
    private ClassPool mPool;
    private List<String> mMethods;
    private String mClassName;
    private CtClass mCtc;
    private String mSuperClass = "com.spring.design.proxy.javassist.dubbo.real.HuaKaiWrapper";
    private Map<String, Method> mCopyMethods;

    public HuaKaiGenerator(ClassPool pool) {
        mPool = pool;
    }

    public static HuaKaiGenerator newInstance(ClassLoader loader) {
        return new HuaKaiGenerator(getClassPool(loader));
    }

    public static ClassPool getClassPool(ClassLoader loader) {
        if (loader == null) {
            return ClassPool.getDefault();
        }

        ClassPool pool = POOL_MAP.get(loader);
        if (pool == null) {
            pool = new ClassPool(true);
            pool.appendClassPath(new LoaderClassPath(loader));
            POOL_MAP.put(loader, pool);
        }
        return pool;
    }

    public HuaKaiGenerator addMethod(String code) {
        if (mMethods == null) {
            mMethods = new ArrayList<String>();
        }
        mMethods.add(code);
        return this;
    }

    public Class<?> toClass() {
        return toClass(HuaKaiLoader.getClassLoader(HuaKaiGenerator.class),
                getClass().getProtectionDomain());
    }

    public Class<?> toClass(ClassLoader loader, ProtectionDomain pd) {
        try {
            CtClass ctcs = mSuperClass == null ? null : mPool.get(mSuperClass);
            mCtc = mPool.makeClass(mClassName);
            if (mSuperClass != null) {
                mCtc.setSuperclass(ctcs);
            }
            mCtc.addInterface(mPool.get(DC.class.getName())); // add dynamic class tag.
            if (mMethods != null) {
                for (String code : mMethods) {
                    if (code.charAt(0) == ':') {
                        mCtc.addMethod(CtNewMethod.copy(getCtMethod(mCopyMethods.get(code.substring(1))),
                                code.substring(1, code.indexOf('(')), mCtc, null));
                    } else {
                        mCtc.addMethod(CtNewMethod.make(code, mCtc));
                    }
                }
            }
            mCtc.addConstructor(CtNewConstructor.defaultConstructor(mCtc));
            return mCtc.toClass(loader, pd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private CtMethod getCtMethod(Method m) throws NotFoundException {
        return getCtClass(m.getDeclaringClass())
                .getMethod(m.getName(), getDescWithoutMethodName(m));
    }

    private CtClass getCtClass(Class<?> c) throws NotFoundException {
        return mPool.get(c.getName());
    }

    public static String getDescWithoutMethodName(Method m) {
        StringBuilder ret = new StringBuilder();
        ret.append('(');
        Class<?>[] parameterTypes = m.getParameterTypes();
        for (int i = 0; i < parameterTypes.length; i++) {
            ret.append(getDesc(parameterTypes[i]));
        }
        ret.append(')').append(getDesc(m.getReturnType()));
        return ret.toString();
    }

    public static String getDesc(Class<?> c) {
        StringBuilder ret = new StringBuilder();

        while (c.isArray()) {
            ret.append('[');
            c = c.getComponentType();
        }

        if (c.isPrimitive()) {
            String t = c.getName();
            if ("void".equals(t)) {
                ret.append(JVM_VOID);
            } else if ("boolean".equals(t)) {
                ret.append(JVM_BOOLEAN);
            } else if ("byte".equals(t)) {
                ret.append(JVM_BYTE);
            } else if ("char".equals(t)) {
                ret.append(JVM_CHAR);
            } else if ("double".equals(t)) {
                ret.append(JVM_DOUBLE);
            } else if ("float".equals(t)) {
                ret.append(JVM_FLOAT);
            } else if ("int".equals(t)) {
                ret.append(JVM_INT);
            } else if ("long".equals(t)) {
                ret.append(JVM_LONG);
            } else if ("short".equals(t)) {
                ret.append(JVM_SHORT);
            }
        } else {
            ret.append('L');
            ret.append(c.getName().replace('.', '/'));
            ret.append(';');
        }
        return ret.toString();
    }

    public HuaKaiGenerator setClassName(String name) {
        mClassName = name;
        return this;
    }

    public static interface DC {

    }
}
