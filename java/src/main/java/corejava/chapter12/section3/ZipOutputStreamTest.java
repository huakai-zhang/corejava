package corejava.chapter12.section3;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipOutputStreamTest {
    public static void main(String[] args) throws IOException {
        FileOutputStream fout = new FileOutputStream("E:\\IDEAFile\\corejava\\src\\corejava.chapter12\\JDBCTest\\haha.zip");
        ZipOutputStream zout = new ZipOutputStream(fout);
        for (int i = 0; i < 3; i++) {
            ZipEntry ze = new ZipEntry("hello" + i + ".txt");
            zout.putNextEntry(ze);
            byte[] bytes = {122,104,97,110,103};
            zout.write(bytes);
            zout.closeEntry();
        }
        zout.close();
    }
}
