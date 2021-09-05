package corejava.chapter11.section1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class StreamTest {
    public static void main(String[] args) throws IOException {
        String contents = new String(Files.readAllBytes(Paths.get("E:\\IDEAFile\\corejava\\src\\corejava.chapter9\\section2\\alice30.txt")), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("\\PL+"));
        /*long count = 0;
        for (String w: words) {
            if (w.length() > 12) {
                count++;
            }
        }*/
        long count = words.stream().filter(w -> w.length() > 12).count();
        System.out.println(count);
    }
}
