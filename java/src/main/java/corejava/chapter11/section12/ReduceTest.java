package corejava.chapter11.section12;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Spring Zhang
 * @date 2019/12/18 15:39
 */
public class ReduceTest {
    public static void main(String[] args) {
        List<Integer> values = new ArrayList<>();
        values.add(0);
        values.add(1);
        values.add(2);
        Integer sum = values.stream().reduce(0, (x, y) -> x + y);
        System.out.println(sum);
        List<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("2");
        strings.add("3");
        String s = strings.stream().collect(Collectors.joining(","));
        System.out.println(s);
    }
}
