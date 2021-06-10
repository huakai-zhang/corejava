package com.spring.design.delegate.spring;

/**
 * @author 春阳
 * @date 2021-06-09 15:46
 */
public class HuaKaiFileSystem extends HuaKaiXmlContext {
    public HuaKaiFileSystem() {
        super();
        refresh();
    }

    public static void main(String[] args) {
        new HuaKaiFileSystem();
    }
}
