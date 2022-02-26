package corejava.chapter7.section1;

import java.io.IOException;

/**
 * @author Spring Zhang
 * @date 2019/11/25 15:16
 */
public class FileFormatException extends IOException {
    public FileFormatException() {
    }

    public FileFormatException(String message) {
        super(message);
    }
}
