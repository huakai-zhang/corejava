package corejava.chapter12.section1;

import java.io.*;
import java.util.zip.ZipInputStream;

/**
 * @author Spring Zhang
 * @date 2019/12/19 15:14
 */
public class Test {
    public static void main(String[] args) throws IOException {
        PushbackInputStream pbin = new PushbackInputStream(new BufferedInputStream(
           new FileInputStream("employee.dat")));
        int b = pbin.read();
        if (b != '<') {
            pbin.unread(b);
        }
        DataInputStream din = new DataInputStream(pbin = new PushbackInputStream(new BufferedInputStream(
                new FileInputStream("employee.dat"))));

        if (b != '<') {
            pbin.unread(b);
        }

        ZipInputStream zin = new ZipInputStream(new FileInputStream("employee.zip"));
        DataInputStream in = new DataInputStream(zin);
    }
}
