package corejava.chapter12.section7;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * @author Spring Zhang
 * @date 2019/12/27 17:40
 */
public class MatcherTest {
    public static void main(String[] args) {
        /*Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher("a78w68s6f7r2388cv65a7s6de5f7se");
        String output = matcher.replaceAll("#");
        System.out.println(output);*/

        Pattern pattern = Pattern.compile("\\s*\\p{Punct}\\s*");
        String[] tokens = pattern.split("dawda,dawdaw,dwadaw,dfegtr,fesfesges");
        /*// 如果有多个标记，可以惰性的获取它们
        Stream<String> stream = pattern.splitAsStream("");
        // 如果不关心预编译模式和惰性获取，可以使用String.split方法
        String[] tokens = input.split("\\s*,\\s*");*/
        System.out.println(Arrays.toString(tokens));
    }
}
