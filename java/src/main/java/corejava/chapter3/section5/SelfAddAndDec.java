package corejava.chapter3.section5;

public class SelfAddAndDec {
    public static void main(String[] args) {
        int m = 7;
        int n = 7;
        int a = 2 * ++m;
        int b = 2 * n++;
        System.out.println(a + ", " + m);
        System.out.println(b + ", " + n);
        // 16, 8
        // 14, 8
    }
}
