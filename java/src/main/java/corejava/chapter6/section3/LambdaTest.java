package corejava.chapter6.section3;

import java.util.Arrays;
import java.util.Date;
import javax.swing.*;

/**
 * @author Spring Zhang
 * @date 2019/11/19 15:09
 */
public class LambdaTest {
    public static void main(String[] args) {
        repeat(10, () -> System.out.println("Hello,World!"));
        repeat(10, i -> System.out.println("倒计时：" + (9 - i)));

        String[] planets = new String[] { "Mercury", "Venus", "Earth", "Mars",
            "Jupiter", "Saturn", "Uranus", "Neptune" };
        System.out.println(Arrays.toString(planets));
        System.out.println("按字典顺序排序：");
        Arrays.sort(planets);
        System.out.println(Arrays.toString(planets));

        System.out.println("按长度排序：");
        Arrays.sort(planets, (first, second) -> first.length() - second.length());
        System.out.println(Arrays.toString(planets));

        Timer t = new Timer(1000, event -> System.out.println("时间是：" + new Date()));
        t.start();

        JOptionPane.showConfirmDialog(null, "退出程序？");
        System.exit(0);
    }

    public static void repeat(int n, Runnable action) {
        for (int i = 0; i < n; i++) {
            action.run();
        }
    }

    public static void repeat(int n, IntConsumer action)
    {
        for (int i = 0; i < n; i++) {
            action.accept(i);
        }
    }

}
