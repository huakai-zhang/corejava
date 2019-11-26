package chapter6.section2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.*;

public class TimerTest {
    public static void main(String[] args) {
        ActionListener listener = new TimePrinter();

        Timer t = new Timer(11000, listener);
        t.start();

        JOptionPane.showMessageDialog(null, "退出程序？");
        System.exit(0);
    }
}

class TimePrinter implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("目前时间是：" + new Date());
        Toolkit.getDefaultToolkit().beep();
    }
}
