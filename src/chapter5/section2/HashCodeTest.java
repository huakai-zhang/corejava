package chapter5.section2;

import java.util.Arrays;

public class HashCodeTest {
    public static void main(String[] args) {
        String s = "OK";
        StringBuilder sb = new StringBuilder(s);
        System.out.println(s.hashCode() + " " + sb.hashCode());
        String t = new String("OK");
        StringBuilder st = new StringBuilder(t);
        System.out.println(t.hashCode() + " " + st.hashCode());
        // 2524 356573597
        // 2524 1735600054
        System.out.println(System.out);
        // java.io.PrintStream@14ae5a5

        int[] numbers = { 2, 3, 5, 7 };
        String ss = "" + numbers;
        System.out.println(ss);
        System.out.println(Arrays.toString(numbers));
        // [I@7f31245a
        // [2, 3, 5, 7]
    }
}
