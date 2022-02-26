package corejava.chapter7.section5;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Spring Zhang
 * @date 2019/11/26 13:32
 */
public class LoggerTest {
    /**
     * 在请求日志记录器时，可以指定资源包
     */
    private static final Logger myLogger = Logger.getLogger("loggerName", "corejava.chapter7.section5.logmessages");

    public static void main(String[] args) throws IOException {
        Logger.getGlobal().info("File->Open menu item selected");
        Logger.getGlobal().fine("File");
        // 为日志指定资源包的关键字，而不是实际的日志消息字符串
        myLogger.info("readingFile");
        // 通常需要在本地化的消息中增加一些参数，可以包含占位符｛0｝，｛1｝等
        myLogger.log(Level.INFO, "moreMessage", new Object[] { "的", "." });

        FileHandler handler = new FileHandler();
        myLogger.addHandler(handler);
        myLogger.info("readingFile");
    }
}
