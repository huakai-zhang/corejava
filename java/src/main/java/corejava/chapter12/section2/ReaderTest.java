package corejava.chapter12.section2;

import java.io.*;

public class ReaderTest {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        //Reader in = new InputStreamReader(new FileInputStream("data.txt"), StandardCharsets.UTF_8);

        PrintWriter out = new PrintWriter("E:\\IDEAFile\\corejava\\src\\corejava.chapter12\\section2\\employee.txt", "UTF-8");
        //PrintWriter out = new PrintWriter(new OutputStreamWriter(new FileOutputStream("E:\\IDEAFile\\corejava\\src\\corejava.chapter12\\section2\\employee.txt"), "UTF-8"), true);
        // 等于
        //PrintWriter out_p = new PrintWriter(new FileOutputStream("employee.txt"), "UTF-8");
        String name = "Harry Hacker";
        double salary = 75000;
        out.print(name);
        out.print("\r\n");
        out.println(salary);
        out.close();
    }
}
