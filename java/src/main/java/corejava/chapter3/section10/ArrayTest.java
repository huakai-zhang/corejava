package corejava.chapter3.section10;

import java.util.Arrays;

public class ArrayTest {
    public static void main(String[] args) {
        int[] smallPrimes = {2, 3, 5, 7, 11, 13};

        //int[] luckNumber = smallPrimes;
        int[] luckNumber = Arrays.copyOf(smallPrimes, smallPrimes.length);
        luckNumber = Arrays.copyOf(luckNumber, luckNumber.length * 2);
        luckNumber[5] = 12;

        System.out.println(Arrays.toString(smallPrimes));
        System.out.println(Arrays.toString(luckNumber));
        // [2, 3, 5, 7, 11, 13]

    }
}
