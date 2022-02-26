package corejava.chapter7.section1;

import java.util.Scanner;

/**
 * @author Spring Zhang
 * @date 2019/11/26 10:12
 */
public class StackTraceTest {
    public static void main(String[] args) {
        /*Throwable t1 = new Throwable();
        StringWriter out = new StringWriter();
        t1.printStackTrace(new PrintWriter(out));
        String description = out.toString();*/

        /*Throwable t2 = new Throwable();
        StackTraceElement[] frames = t2.getStackTrace();
        for (StackTraceElement frame : frames) {

        }*/

        /*Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();
        for (Thread t : map.keySet()) {
            StackTraceElement[] frames = map.get(t);
        }*/
        Scanner in = new Scanner(System.in);
        System.out.print("输入 n:" );
        int n = in.nextInt();
        factorial(n);
    }

    public static int factorial(int n) {
        System.out.println("阶层(" + n + "):");
        Throwable t = new Throwable();
        StackTraceElement[] frames = t.getStackTrace();
        for (StackTraceElement f : frames) {
            System.out.println(f);
        }
        int r;
        if (n <= 1) {
            r = 1;
        } else {
            r = n * factorial(n - 1);
        }
        System.out.println("返回 " + r);
        return r;
    }
}
