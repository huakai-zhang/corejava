package com.spring.design.strategy.op_code;

import java.util.Map;

public class ColorContext {
    public static Color getInstance(String color){
        Color inter=null;
        Map<String, String> allClazz = ColorEnum.getAllClazz();
        String clazz = allClazz.get(color);
        if (color!=null&&color.trim().length()>0) {
            try {
                try {
                    inter = (Color) Class.forName(clazz).newInstance();//调用无参构造器创建实例
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return inter;
    }
}
