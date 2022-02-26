package corejava.chapter12.section3;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class ZipTest {
    public static void main(String[] args) throws IOException {
        ZipFile zf = new ZipFile("E:\\IDEAFile\\corejava\\src\\corejava.chapter12\\JDBCTest\\first.zip");
        ZipInputStream zin = new ZipInputStream(new FileInputStream("E:\\IDEAFile\\corejava\\src\\corejava.chapter12\\JDBCTest\\first.zip"));
        ZipEntry entry;
        while ((entry = zin.getNextEntry()) != null) {
            InputStream inputStream = zf.getInputStream(entry);
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            zin.closeEntry();
        }
        zin.close();
    }
}
