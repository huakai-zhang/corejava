package practice.java.recursion;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/9/7
 */
public class TestStep {

    public static void main(String[] args) {
        System.out.println(loop(40));
    }

    public static int f(int n) {
        if (n < 1) {
            throw new IllegalArgumentException(n + "不能小于1");
        }
        if(n == 1 || n == 2) {
            return n;
        }
        return f(n - 2) + f(n - 1);
    }

    public static int loop(int n) {
        if (n < 1) {
            throw new IllegalArgumentException(n + "不能小于1");
        }
        if(n == 1 || n == 2) {
            return n;
        }
        
        int one = 1;
        int two = 2;
        int sum = 0;
        for (int i = 3; i <= n; i++) {
            sum = two + one;
            one = two;
            two = sum;
        }
        return sum;
    }
}
