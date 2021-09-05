package corejava.chapter7.section1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;

public class ExceptionTest {
    public void read(String filename) {
        try {
            InputStream in = new FileInputStream(filename);
            int b;
            while ((b = in.read()) != -1) {
                // 输入
            }
        } catch (FileNotFoundException | UnknownHostException e) {

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /*public void read(String filename) throws IOException {
        InputStream in = new FileInputStream(filename);
        int b;
        while ((b = in.read()) != -1) {
            // 输入
        }
    }*/
}
