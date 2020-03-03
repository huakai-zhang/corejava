package chapter6.section3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * @author Spring Zhang
 * @date 2019/11/19 16:52
 */
public class TimedGreeter extends  Greeter {
    /*@Override
    public void greet() {
        Timer t = new Timer(100, super::greet);
        t.start();
    }

    public static void repeatMessage(String text, int delay) {
        ActionListener listener = event -> {
            System.out.println(text);
            Toolkit.getDefaultToolkit().beep();
        };
        new Timer(delay, listener).start();
    }

    public static void countDown(int start, int delay) {
        ActionListener listener = event -> {
            start--; //无法改变捕获的变量
            System.out.println(start);
        };
        new Timer(delay, listener).start();
    }

    public static void repeat(String test, int count) {
        for (int i = 1; i <= count; i++) {
            ActionListener listener = event -> {
                System.out.println(i + ": " + test);  // 不能改变i，不合法
            };
            new Timer(1000, listener).start();
        }
    }
*/
}
