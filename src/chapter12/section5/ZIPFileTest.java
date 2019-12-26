package chapter12.section5;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * @author Spring Zhang
 * @date 2019/12/26 14:45
 */
public class ZIPFileTest {
    public static void main(String[] args) throws IOException {
        String zipname = null;
        FileSystem fs = FileSystems.newFileSystem(Paths.get(zipname), null);
        /*Files.copy(fs.getPath(""), targetPath)*/
        Files.walkFileTree(fs.getPath("/"), new SimpleFileVisitor<Path>(){
           @Override
           public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
               System.out.println(file);
               return FileVisitResult.CONTINUE;
           }
        });
    }
}
