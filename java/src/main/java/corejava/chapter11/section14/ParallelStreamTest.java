package corejava.chapter11.section14;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ParallelStreamTest {
    public static void main(String[] args) throws IOException {
        List<String> words = new ArrayList<>();
        //Stream<String> parallelWords = words.parallelStream();
        String[] strings = {"Amy", "Mack"};
        //Stream<String> parallelWords = Stream.of(strings).parallel();

        String contents = new String(Files.readAllBytes(Paths.get("E:\\IDEAFile\\corejava\\src\\corejava.chapter9\\section2\\alice30.txt")), StandardCharsets.UTF_8);
        List<String> wordList = Arrays.asList(contents.split("\\PL+"));
        /*int[] shortWords = new int[12];
        wordList.parallelStream().forEach(
                s -> { if (s.length() < 12) { shortWords[s.length()]++; }});
        System.out.println(Arrays.toString(shortWords));*/

        Map<Integer, Long> shortWordCounts = wordList.parallelStream().
                filter(s -> s.length() < 10).collect(Collectors.groupingBy(String::length, Collectors.counting()));
        System.out.println(shortWordCounts);

        Map<Integer, List<String>> result = words.parallelStream().collect(
                Collectors.groupingByConcurrent(String::length));

        Map<Integer, Long> wordCounts = words.parallelStream().collect(
                Collectors.groupingByConcurrent(String::length, Collectors.counting()));

    }
}
