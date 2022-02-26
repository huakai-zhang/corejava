package practice.java.sync;

import java.util.concurrent.TimeUnit;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/9/7
 */
class MyThread {

    public synchronized static void staticMethod1() {
        try { TimeUnit.SECONDS.sleep(4); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("staticMethod1");
    }

    public synchronized static void staticMethod2() {
        //try { TimeUnit.SECONDS.sleep(4); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("staticMethod2");
    }

    public static void noStaticMethod() {
        try { TimeUnit.SECONDS.sleep(4); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("noStaticMethod");
    }

}
public class SynchronizeTest {
    public static void main(String[] args) throws InterruptedException {
        MyThread myThread1 = new MyThread();
        MyThread myThread2 = new MyThread();
        /*new Thread(() -> myThread1.staticMethod1()).start();
        Thread.sleep(100);
        new Thread(() -> myThread1.staticMethod2()).start();
        Thread.sleep(100);*/
        new Thread(() -> myThread1.noStaticMethod()).start();
        new Thread(() -> myThread2.noStaticMethod()).start();
    }
}
