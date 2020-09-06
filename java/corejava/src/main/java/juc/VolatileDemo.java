package juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyData {  // MyData.java ===> MyData.class ===> JVM字节码
    volatile int number = 0;

    public void addTo60() {
        this.number = 60;
    }

    // 请注意, 此时 number 前面是加了 volatile 关键字修饰的, volatile 不保证原子性
    public void addPlusPlus(){
        number++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();
    public void addAtomic(){
        atomicInteger.getAndIncrement();
    }
}

/**
 * 1、验证 volatile 的可见性
 *   1.1 假如 int number=0; number 变量之前根本没有添加 volatile 关键字修饰, 没有可见性
 *   1.2 添加了 volatile, 可以解决可见性问题
 *
 * 2、验证 volatile 不保证原子性
 *   2.1 原子性指的是什么意思？
 *       不可分割, 完整性, 也即某个线程正在做某个具体业务时, 中间不可以被加塞或者被分割. 需要整体完整
 *       要么同时成功, 要么同时失败
 *
 *   2.2 volatile 不保证原子性的案例演示
 *
 *   2.3 why
 *
 *   2.4 如何解决原子性？
 *     * 加 synchronized
 *     * 使用我们的 JUC 下 AtomicInteger
 */
public class VolatileDemo {
    public static void main(String[] args) {
        MyData myData = new MyData();
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    myData.addPlusPlus();
                    myData.addAtomic();
                }
            }, String.valueOf(i)).start();
        }

        // 需要等待上面20个线程都全部计算完成, 再用main线程取得最终的结果值看是多少？
        while (Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + "\t int type, finally number value: " + myData.number);
        System.out.println(Thread.currentThread().getName() + "\t AtomicInteger type, finally number value: " + myData.atomicInteger);
    }

    // volatile 可以保证可见性, 及时通知其它线程, 主物理内存的值已经被修改
    public static void seeOkByVolatile() {
        MyData myData = new MyData();  // 资源类

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addTo60();
            System.out.println(Thread.currentThread().getName() + "\t update number value: " + myData.number);
        }, "AAA").start();

        // 第2个线程就是我们的main线程
        while (myData.number == 0){
            // main 线程就一直在这里等待循环,直到 number 值不再等于零
        }
        System.out.println(Thread.currentThread().getName() + "\t mission is over, main get number value: " + myData.number);
    }
}
