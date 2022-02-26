package com.spring.design.strategy.op_code;

import java.util.HashMap;
import java.util.Map;

public class StrategyDemo {

    private static Map<String, Color> map = new HashMap<String, Color>() {
        {
            put("black", new Black());
            put("blue", new Blue());
            put("red", new Red());
        }
    };

    public static void main(String[] args) {
        String color = "black";

        // 1.if-else
        test1(color);

        // 2.map集合实现的策略模式
        Color color1 = map.get(color);
        color1.doSomeThing();

        // 3.枚举
        ColorStrategyEnum color2 = ColorStrategyEnum.valueOf("BLACK");
        color2.doSomeThing();

        // 4.枚举+反射实现的策略模式
        Color color3 = ColorContext.getInstance(color);
        color3.doSomeThing();

        System.out.println(getDays1(10));
        System.out.println(getDays2(10));
    }

    public static void test1(String color) {
        if ("black".equals(color)) {
            System.out.println("我是黑色");
        } else if ("blue".equals(color)) {
            System.out.println("我是蓝色");
        } else if ("red".equals(color)) {
            System.out.println("我是红色");
        }
    }

    static int getDays1(int month){
        if (month == 1)  return 31;
        if (month == 2)  return 29;
        if (month == 3)  return 31;
        if (month == 4)  return 30;
        if (month == 5)  return 31;
        if (month == 6)  return 30;
        if (month == 7)  return 31;
        if (month == 8)  return 31;
        if (month == 9)  return 30;
        if (month == 10)  return 31;
        if (month == 11)  return 30;
        if (month == 12)  return 31;
        return 0;
    }

    static int getDays2(int month){
        int[] monthDays = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        return monthDays[--month];
    }
}
