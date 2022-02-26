package corejava.chapter11.section4;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Spring Zhang
 * @date 2019/12/17 10:32
 */
public class ChildStreamTest {
    public static void main(String[] args) {
        Stream<Double> randoms = Stream.generate(Math::random).limit(100);
        randoms.forEach(System.out::println);

        Stream<String> combined = Stream.concat(letters("Hello"), letters("World"));
        combined.forEach(System.out::println);
    }

    public static Stream<String> letters(String s) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            result.add(s.substring(i, i + 1));
        }
        return result.stream();
    }
}
