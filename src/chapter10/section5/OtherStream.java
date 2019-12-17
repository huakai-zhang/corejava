package chapter10.section5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Spring Zhang
 * @date 2019/12/17 11:04
 */
public class OtherStream {
    public static void main(String[] args) {
        Stream<String> uniqueWords = Stream.of("merrily", "merrily", "merrily", "merrily").distinct();
        uniqueWords.forEach(System.out::println);

        List<String> words = new ArrayList<>();
        words.add("Amy");
        words.add("Mack");
        words.add("Spring");
        Stream<String> longestFirst = words.stream().sorted(Comparator.comparing(String::length).reversed());
        longestFirst.forEach(System.out::println);


        Object[] powers = Stream.iterate(1.0, p -> p * 2).peek(e -> System.out.println("Fetching " + e)).limit(20).toArray();
        System.out.println(powers);
    }
}
