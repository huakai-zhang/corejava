package com.spring.design.strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Spring Zhang
 * @date 2020/3/5 11:43
 */
public class MyListTest {
    public static void main(String[] args) {
        //new MyList().sort(new );

        // 策略模式
        List<Long> numbers = new ArrayList<>();
        Collections.sort(numbers, new Comparator<Long>() {
            @Override
            // 返回值是固定的，-1，0，1
            public int compare(Long o1, Long o2) {
                // 中间逻辑是不一样的
                return 0;
            }
        });
    }
}
