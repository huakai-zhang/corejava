package chapter6.section4;

import com.sun.org.apache.xpath.internal.SourceTree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Date;

public class InnerClassTest {

    public static void main(String[] args) {
        int[] counter = new int[1];
        Date[] dates = new Date[100];
        for (int i = 0; i < dates.length; i++) {
            dates[i] = new Date() {
                @Override
                public int compareTo(Date other) {
                    counter[0]++;
                    return super.compareTo(other);
                }
            };
        }
        Arrays.sort(dates);
        System.out.println(counter + " 比较。");

        TalkingClock clock = new TalkingClock(1000, false);
        clock.start(10000, true);

        JOptionPane.showMessageDialog(null, "退出程序？");
        System.exit(0);
    }
}

class TalkingClock {
    private int interval;
    private boolean beep;

    public TalkingClock(int interval, boolean beep) {
        this.interval = interval;
        this.beep = beep;
    }
    public void start(int interval, boolean beep) {
        // 内部类
        class TimePrinter implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("时间是：" + new Date());
                if (beep) {
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        }

        ActionListener listener = new TimePrinter();
        Timer t = new Timer(interval, listener);
        t.start();
    }


}