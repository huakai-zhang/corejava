package corejava.chapter12.section5;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * @author Spring Zhang
 * @date 2019/12/26 13:31
 */
public class FilesList {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("E:\\Java\\IdeaProjects\\corejava\\src");
        //try(Stream<Path> entries = Files.walk(path)) {
           // entries.forEach(p -> System.out.println(p.getFileName()));
        //}

        /*try (DirectoryStream<Path> directories = Files.newDirectoryStream(path, "**.java")) {
            for (Path directory : directories) {
                System.out.println(directory.getFileName());
            }
        }*/

        Files.walkFileTree(path, new SimpleFileVisitor<Path>(){
            @Override
            public FileVisitResult visitFile(Path p, BasicFileAttributes attrs) {
                System.out.println(p);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
               return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path p, IOException exc) {
                return FileVisitResult.SKIP_SUBTREE;
            }
        });

        Files.walkFileTree(path, new SimpleFileVisitor<Path>(){
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException e) throws IOException {
                if (e != null) {
                    throw e;
                }
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }
}
