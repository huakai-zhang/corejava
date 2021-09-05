package corejava.chapter12.section5;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathTest {
    public static void main(String[] args) throws IOException {
        /*Path absolute = Paths.get("D:\\", "test", "test2.txt");
        Path relative = Paths.get("test", "test.txt");
        List<String> lines = Files.readAllLines(absolute);
        System.out.println(lines);
        lines = Files.readAllLines(relative);
        System.out.println(lines);*/

        /*Properties props = new Properties();
        String baseDir = props.getProperty("base.dir");
        Path basePath = Paths.get(baseDir);*/

        Path basePath = Paths.get("E:\\JAVA\\IdeaProjects\\corejava\\src");
        Path workRelative = Paths.get("chapter3");
        Path chapter3 = basePath.resolve(workRelative);
        Path chapter4 = chapter3.resolveSibling("chapter4");
        System.out.println(chapter3.toString());
        System.out.println(chapter4.toString());
        //E:\JAVA\IdeaProjects\corejava\src\corejava.chapter3
        //E:\JAVA\IdeaProjects\corejava\src\corejava.chapter4
    }
}
