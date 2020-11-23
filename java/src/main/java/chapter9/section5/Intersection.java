package chapter9.section5;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Intersection {
    public static void main(String[] args) {
        Set<String> a = new HashSet<>();
        a.add("Amy");
        a.add("Bob");
        Set<String> b = new HashSet<>();
        b.add("Mack");
        b.add("Bob");
        Set<String> result = new HashSet<>(a);
        result.retainAll(b);
        System.out.println(result);

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }
        List<Integer> relocated = new ArrayList<>();
        relocated.addAll(list.subList(0, 10));
        // 子范围还可以完成更改操作
        list.subList(0, 10).clear();
        System.out.println(list);
        System.out.println(relocated);
        // [10, 11, 12, 13, 14, 15, 16, 17, 18, 19]
        // [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
    }
}
