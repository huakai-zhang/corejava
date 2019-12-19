package chapter12.section1;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Spring Zhang
 * @date 2019/12/19 13:33
 */
public class InputStreamTest {
    public static void main(String[] args) {
        try {
            FileInputStream in = new FileInputStream("E:/Java/IdeaProjects/corejava/src/chapter12/section1/1.txt");
            int b;
            while ((b = in.read()) != -1) {
                System.out.println(b + " " + (char) b);
            }
            in.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
