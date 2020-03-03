package chapter12.section7;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * @author Spring Zhang
 * @date 2019/12/27 16:21
 */
public class PatternTest {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("[a-z]*ab", Pattern.CASE_INSENSITIVE + Pattern.UNICODE_CASE);
        Matcher matcher = pattern.matcher("cab");
        System.out.println(matcher.matches());
        String regex = "(?iU:expression)";

        /*Stream<String> strings = ...;
        Stream<String> result = strings.filter(pattern.asPredicate());*/
    }
}
