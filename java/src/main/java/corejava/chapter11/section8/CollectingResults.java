package corejava.chapter11.section8;

import corejava.chapter4.section3.Employee;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectingResults {
    public static Stream<String> noVowels() throws IOException {
        String contents = new String(Files.readAllBytes(Paths.get("E:\\IDEAFile\\corejava\\src\\corejava.chapter9\\section2\\alice30.txt")), StandardCharsets.UTF_8);
        List<String> wordList = Arrays.asList(contents.split("\\PL+"));
        Stream<String> words = wordList.stream();
        return words.map(s -> s.replaceAll("[aeiouAEIOU]", ""));
    }

    public static <T> void show(String label, Set<T> set) {
        System.out.print(label + ": " + set.getClass().getName());
        System.out.println("["
            + set.stream().limit(10).map(Object::toString)
                .collect(Collectors.joining(", ")) + "]");
    }

    public static void main(String[] args) throws IOException {
        Iterator<Integer> iter = Stream.iterate(0, n -> n + 1).limit(10).iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }

        Object[] numbers = Stream.iterate(0, n -> n + 1).limit(10).toArray();
        System.out.println("Object array:" + Arrays.toString(numbers));

        try {
            Integer number = (Integer) numbers[0];
            System.out.println("number:" + number);
            System.out.println("The following statement throws an exception:");
            Integer[] number2 = (Integer[]) numbers;
        } catch (ClassCastException ex) {
            System.out.println(ex);
        }

        Integer[] number3 = Stream.iterate(0, n-> n + 1).limit(10).toArray(Integer[]::new);
        System.out.println("Integer array:" + Arrays.toString(number3));

        Set<String> noVowelSet = noVowels().collect(Collectors.toSet());
        show("noVowelSet", noVowelSet);

        TreeSet<String> noVowelTreeSet = noVowels().collect(Collectors.toCollection(TreeSet::new));
        show("noVowelTreeSet", noVowelTreeSet);

        String result = noVowels().limit(10).collect(Collectors.joining());
        System.out.println("Joining: " + result);
        result = noVowels().limit(10).collect(Collectors.joining(", "));
        System.out.println("Joining with commas: " + result);

        List<Employee> personList = new ArrayList<Employee>();
        personList.add(new Employee("Tom", 8900));
        personList.add(new Employee("Jack", 7000));
        personList.add(new Employee("Lily", 9000));

        Double averageSalary = personList.stream().collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(averageSalary);  // 结果：8300

        IntSummaryStatistics summary = noVowels().collect(Collectors.summarizingInt(String::length));
        double averageWordLength = summary.getAverage();
        double maxWordLength = summary.getMax();
        System.out.println("Average word length: " + averageWordLength);
        System.out.println("Max word length: " + maxWordLength);
        System.out.println("forEach:");
        noVowels().limit(10).forEach(System.out::println);
    }

}
