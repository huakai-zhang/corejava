package corejava.chapter12.section6;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ByteBufferTest {
    public static void main(String[] args) throws IOException {
        /*ByteBuffer buffer = ByteBuffer.allocate(RECORD_SIZE);*/
        /*channel.read(buffer);*/
        /*channel.position(newpos);*/
        /*buffer.flip();*/
        /*channel.write(buffer);*/
        Path path = Paths.get("");
        FileChannel channel = FileChannel.open(path);
        FileLock lock = channel.lock();
        // 或
        // FileLock lock = channel.tryLock();
    }
}
