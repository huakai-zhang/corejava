package chapter12.section5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class PathTest {
    public static void main(String[] args) throws IOException {
        Path absolute = Paths.get("D:\\", "test", "test2.txt");
        Path relative = Paths.get("test", "test.txt");
        List<String> lines = Files.readAllLines(absolute);
        System.out.println(lines);
        lines = Files.readAllLines(relative);
        System.out.println(lines);
    }
}
