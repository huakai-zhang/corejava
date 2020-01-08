package chapter10.section5;

import java.text.SimpleDateFormat;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Spring Zhang
 * @date 2020/1/8 14:17
 */
public class Test {
    /*private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();*/
    /*private Lock readLock = rwl.readLock();*/
    /*private Lock writeLock = rwl.writeLock();*/


    /*public double getTotalBalance() {*/
    /*    readLock.lock();*/
    /*    try {*/
    /*        ...*/
    /*    } finally {*/
    /*        readLock.unlock();*/
    /*    }*/
    /*}*/

    /*public void transfer(...) {*/
    /*    writeLock.lock();*/
    /*    try {*/
    /*        ...*/
    /*    } finally {*/
    /*        writeLock.unlock();*/
    /*    }*/
    /*}*/

    public static final ThreadLocal<SimpleDateFormat> dateFormat =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));

    public static void main(String[] args) {

    }
}
