package corejava.chapter3.section10;

import java.util.Arrays;
import java.util.Scanner;

public class ChouJiang {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("你需要抓取多少个数字？");
        int k = in.nextInt();
        System.out.print("你需要抓取的最大数字？");
        int n = in.nextInt();
        int[] numbers = new int[n];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i + 1;
        }
        int[] result = new int[k];

        for (int i = 0; i < result.length; i++) {
            int r = (int)(Math.random() * n);
            result[i] = numbers[r];
            numbers[r] = numbers[n - 1];
            n--;
        }

        Arrays.sort(result);
        System.out.println("结果：");
        for (int r : result) {
            System.out.println(r);
        }
    }
}
