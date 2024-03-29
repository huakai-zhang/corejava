package corejava.chapter11.section14;

import static java.util.stream.Collectors.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ParallelStreams {
    public static void main(String[] args) throws IOException {
        String contents = new String(Files.readAllBytes(Paths.get("E:\\IDEAFile\\corejava\\src\\corejava.chapter9\\section2\\alice30.txt")), StandardCharsets.UTF_8);
        List<String> wordList = Arrays.asList(contents.split("\\PL+"));

        // 代码很糟糕
        int[] shortWords = new int[10];
        wordList.parallelStream().forEach(s -> {
            if (s.length() < 10) {
                shortWords[s.length()]++;
            }
        });
        System.out.println(Arrays.toString(shortWords));

        //再试一次结果可能会不同（也可能是错误的）
        Arrays.fill(shortWords, 0);
        wordList.parallelStream().forEach(s ->{
            if (s.length() < 10) {
                shortWords[s.length()]++;
            }
        });
        System.out.println(Arrays.toString(shortWords));

        // 补救措施：分组计数
        Map<Integer, Long> shortWordCounts = wordList.parallelStream()
                .filter(s -> s.length() < 10)
                .collect(groupingBy(String::length, counting()));
        System.out.println(shortWordCounts);

        Map<Integer, List<String>> result = wordList.parallelStream().collect(
                groupingByConcurrent(String::length));
        System.out.println(result.get(14));

        result = wordList.parallelStream().collect(
                groupingByConcurrent(String::length));
        System.out.println(result.get(14));

        Map<Integer, Long> wordCounts = wordList.parallelStream().collect(
                groupingByConcurrent(String::length, counting()));
        System.out.println(wordCounts);
    }
}
