package corejava.chapter9.section6;

import java.util.BitSet;

public class Sieve {
    public static void main(String[] args) {
        int n = 211;
        long start = System.currentTimeMillis();
        BitSet b = new BitSet(n + 1);
        int count = 0;
        int i;
        for (i = 2; i <= n; i++) {
            b.set(i);
        }
        i = 2;
        while (i * i <= n) {
            if (b.get(i)) {
                count++;
                int k = 2 * i;
                while (k <= n) {
                    b.clear(k);
                    k += i;
                }
            }
            i++;
        }
        while (i <= n) {
            if (b.get(i)) {
                count++;
            }
            i++;
        }
        long end = System.currentTimeMillis();
        System.out.println(count + " 个素数");
        System.out.println((end - start) + " 毫秒");
        //148933 个素数
        //41 毫秒
    }
}
