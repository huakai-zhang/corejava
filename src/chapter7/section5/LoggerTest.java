package chapter7.section5;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Spring Zhang
 * @date 2019/11/26 13:32
 */
public class LoggerTest {
    private static final Logger myLogger = Logger.getLogger("chapter7.section5");

    public static void main(String[] args) {
        Logger.getGlobal().info("File->Open menu item selected");
        Logger.getGlobal().setLevel(Level.OFF);
        Logger.getGlobal().info("File");
    }
}
