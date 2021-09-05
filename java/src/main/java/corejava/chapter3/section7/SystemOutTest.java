package corejava.chapter3.section7;

public class SystemOutTest {
    public static void main(String[] args) {
        double x = -1000.0 / 3.0;
        System.out.println(x);
        System.out.printf("%(8.2f", x);

        int i = 1000000;
        System.out.printf("%,d", i);
        // 1,000,000
        System.out.println();
        int ii = 17;
        System.out.printf("%d%<x", ii);
    }
}
