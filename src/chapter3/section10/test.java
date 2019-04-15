package chapter3.section10;

import java.util.Arrays;

public class test {
    public static void main(String[] args) {
        String[] s = {"A","B","C"};
        String[] ss = Arrays.copyOf(s, 2);
        System.out.println(Arrays.toString(ss));
    }
}
