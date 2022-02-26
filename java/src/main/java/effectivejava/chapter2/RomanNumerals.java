package effectivejava.chapter2;

import java.util.regex.Pattern;

/**
 * @author zhangchunyang
 * @since 2021/9/12 10:50
 * 避免创建不必要的对象
 */
public class RomanNumerals {

    private static final Pattern ROMAN = Pattern.compile("^(?=.)M*(C[MD]|D?C{0,3})(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");

    public static void main(String[] args) {
        // String s = new String("haha"); // DON't DO THIS!!!

        String s = "hahahaha";

        long start = System.currentTimeMillis();
        /*for (int i = 0; i < 1000000; i++) {
            System.out.println(RomanNumerals.isRomanNumeral(s));
        }*/
        System.out.println(RomanNumerals.sum());
        System.out.println(System.currentTimeMillis() - start);
    }

    static boolean isRomanNumeral(String s) {
        // String.matchers 方法内每次都会创建一个 Pattern 实例，却只用了一次
        // return s.matches("^(?=.)M*(C[MD]|D?C{0,3})(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");

        // 为了提高性能，应该显式地姜正则表达式编译成一个 Pattern 实例
        return ROMAN.matcher(s).matches();
    }

    static long sum() {
        // Long sum = 0L; // DON't DO THIS!!!
        long sum = 0L;
        for (long i = 0; i <= Integer.MAX_VALUE; i++) {
            sum += i;
        }
        return sum;
    }
}
