package chapter12.section2;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author Spring Zhang
 * @date 2019/12/20 13:08
 */
public class BufferedReaderTest {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream("E:\\JAVA\\IdeaProjects\\corejava\\src\\chapter12\\section2\\f.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        String line;
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }
    }
}
