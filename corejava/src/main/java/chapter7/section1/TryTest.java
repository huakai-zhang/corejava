package chapter7.section1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * @author Spring Zhang
 * @date 2019/11/26 10:01
 */
public class TryTest {
    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner in = new Scanner(new FileInputStream("E://test.txt"), "UTF-8");
             PrintWriter out = new PrintWriter("out.text")) {
            while (in.hasNext()) {
                out.println(in.next().toUpperCase());
            }
        }
    }
}
