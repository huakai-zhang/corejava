package singleton.seriable;

import java.io.Serializable;
import java.time.Instant;

/**
 * 序列化与反序列化保证单例，重写readResolve
 * @author Spring Zhang
 * @date 2020/3/4 16:58
 */
public class Seriable implements Serializable {

    public final static Seriable INSTANCE = new Seriable();

    private Seriable(){}

    public static Seriable getInstance() {
        return INSTANCE;
    }

    // 解决单例序列化问题
    private Object readResolve() {
        return INSTANCE;
    }
}
