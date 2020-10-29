package com.spring.design.strategy.op_code;

import java.util.HashMap;
import java.util.Map;

public enum  ColorEnum {

    MS066("black", "com.spring.design.strategy.op_code.Black"),
    MS034("blue", "com.spring.design.strategy.op_code.Blue"),
    MS064("red", "com.spring.design.strategy.op_code.Red");

    private String color;
    private String clazz;

    ColorEnum(String color, String clazz) {
        this.color = color;
        this.clazz = clazz;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public static Map<String, String> getAllClazz() {
        Map<String, String> map = new HashMap<>();
        for (ColorEnum colorEnum : ColorEnum.values()) {
            map.put(colorEnum.getColor(), colorEnum.getClazz());
        }
        return map;
    }
}
