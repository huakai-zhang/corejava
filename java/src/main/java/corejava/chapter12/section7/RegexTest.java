package corejava.chapter12.section7;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Spring Zhang
 * @date 2019/12/27 17:15
 */
public class RegexTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("输入模式：");
        String patternString = in.nextLine();

        Pattern pattern = Pattern.compile(patternString);

        while (true) {
            System.out.println("输入字符串匹配：");
            String input = in.nextLine();
            if (input == null || input.equals("")) {
                return;
            }
            Matcher matcher = pattern.matcher(input);
            if (matcher.matches()) {
                System.out.println("Match");
                int g = matcher.groupCount();
                if (g > 0) {
                    for (int i = 0; i < input.length(); i++) {
                        // 打印任何群组
                        for (int j = 1; j <= g; j++) {
                            if (i == matcher.start(j) && i == matcher.end(j)) {
                                System.out.print("()");
                            }
                        }
                        // 打印（对于从此处开始的非空组
                        for (int j = 1; j <= g; j++) {
                            if (i == matcher.start(j) && i != matcher.end(j)) {
                                System.out.print("(");
                            }
                        }
                        System.out.print(input.charAt(i));
                        for (int j = 1; j <= g; j++) {
                            if (i + 1 != matcher.start(j) && i + 1 == matcher.end(j)) {
                                System.out.print(")");
                            }
                        }
                    }
                    System.out.println();
                }
            } else {
                System.out.println("No match");
            }
        }
    }
}
// 输入模式：
// (([1-9]|1[0-2]):([0-5][0-9]))[ap]m
// 输入字符串匹配：
// 11:59am
// Match
// ((11):(59))am