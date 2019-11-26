package chapter3.section7;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileTest {
    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(Paths.get("d:\\test.txt"), "UTF-8");
            while (in.hasNextLine()) {
                System.out.println(in.nextLine());
            }
            PrintWriter out = new PrintWriter("myfile.text", "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
