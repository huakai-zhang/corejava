package corejava.chapter12.section5;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.PosixFileAttributes;
import java.util.List;

/**
 * @author Spring Zhang
 * @date 2019/12/25 11:07
 */
public class FilesTest {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("");
        byte[] bytes = Files.readAllBytes(path);
        // 将文件当作字符串读入
        String content = new String(bytes, StandardCharsets.UTF_8);
        // 将文件当做行序列读入
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);

        Files.write(path, content.getBytes(StandardCharsets.UTF_8));
        // 向指定文件追加内容
        Files.write(path, content.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        // 将一个行的集合写出到文件
        Files.write(path, lines);
        InputStream in = Files.newInputStream(path);
        OutputStream out = Files.newOutputStream(path);
        Reader reader = Files.newBufferedReader(path);
        Writer writer = Files.newBufferedWriter(path);

        /*Path newPath = Files.createTempFile(dir, prefix, suffix);
        Path newPath = Files.createTempFile(prefix, suffix);
        Path newPath = Files.createTempDirectory(dir, prefix);
        Path newPath = Files.createTempDirectory(prefix);*/
        /*Files.copy(path, path, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);*/

        BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class);
        PosixFileAttributes posixFileAttributes = Files.readAttributes(path, PosixFileAttributes.class);
    }
}
