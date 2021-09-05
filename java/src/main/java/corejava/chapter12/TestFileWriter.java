package corejava.chapter12;

import java.io.FileWriter;
import java.io.IOException;

public class TestFileWriter {
    public static void main(String[] args) throws IOException {
        FileWriter fw = new FileWriter("E:/2.txt");
        for (int c = 0; c <= 50000; c++) {
            fw.write(c);
        }
        fw.close();
    }
}
