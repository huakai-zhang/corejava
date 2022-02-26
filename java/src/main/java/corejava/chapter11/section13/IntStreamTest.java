package corejava.chapter11.section13;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Spring Zhang
 * @date 2019/12/18 17:07
 */
public class IntStreamTest {
    public static void main(String[] args) {
        int[] values = {1, 1, 2, 3, 5};
        IntStream stream = IntStream.of(1, 1, 2, 3, 5);
        stream = Arrays.stream(values, 2, 4);
        stream.forEach(System.out::println);
        IntStream zeroToNinetyNine = IntStream.range(0 ,100);
        IntStream zeroToHundred = IntStream.rangeClosed(0 ,100);
        //zeroToNinetyNine.forEach(System.out::println);
        //zeroToHundred.forEach(System.out::println);

        String sentence = "\uD835\uDD46 is the set of octonions.";
        IntStream codes = sentence.codePoints();
        codes.forEach(System.out::println);

        Stream<Integer> integers = IntStream.range(0, 100).boxed();
    }
}
