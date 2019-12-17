package chapter10.section6;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Spring Zhang
 * @date 2019/12/17 11:38
 */
public class OptionalTest {
    public static void main(String[] args) {
        List<String> words = new ArrayList<>();
        words.add("Amy");
        words.add("Mack");
        words.add("Spring");
        words.add("Qua");
        words.add("QQ");
        Optional<String> largest = words.stream().max(String::compareToIgnoreCase);
        System.out.println(largest.orElse(""));

        Optional<String> startWithQ = words.stream().filter(s -> s.startsWith("Q")).findFirst();
        System.out.println(startWithQ.orElse(""));

        boolean aWordStartWithQ = words.stream().parallel().anyMatch(s -> s.startsWith("Q"));
        System.out.println(aWordStartWithQ);
    }
}
