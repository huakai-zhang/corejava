package chapter11.section3;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Spring Zhang
 * @date 2019/12/17 9:55
 */
public class FilterTest {
    public static void main(String[] args) {
        List<String> words = new ArrayList<>();
        words.stream().filter(w -> w.length() > 12);
        Stream<String> firstLetters = words.stream().map(s -> s.substring(0, 1));

        words.add("Amy");
        words.add("Mack");
        words.add("boat");
        Stream<String> result = words.stream().flatMap(FilterTest::letters);
        result.forEach(System.out::println);
    }

    public static Stream<String> letters(String s) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            result.add(s.substring(i, i + 1));
        }
        return result.stream();
    }
}
