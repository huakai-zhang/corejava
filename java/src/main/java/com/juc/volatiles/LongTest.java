package com.juc.volatiles;

public class LongTest implements Runnable {
    private volatile static long aLong = 0;
    private volatile long value;

    public LongTest(long value) {
        this.setValue(value);
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    @Override
    public void run() {
        int i = 0;
        while (i < 100000) {
            LongTest.aLong = this.getValue();
            i++;
            // 赋值操作！！
            long temp = LongTest.aLong;
            // 取出值操作！！！
            if (temp != 1L && temp != -1L) {
                System.out.println("出现错误结果" + temp);
                System.exit(0);
            }
        }
        System.out.println("运行正确");
    }

    public static void main(String[] args) throws InterruptedException {
        // 获取并打印当前JVM是32位还是64位的
        String sysNum = System.getProperty("sun.arch.data.model");
        System.out.println("系统的位数："+sysNum);
        LongTest t1 = new LongTest(1);
        LongTest t2 = new LongTest(-1);
        Thread T1 = new Thread(t1);
        Thread T2 = new Thread(t2);
        T1.start();
        T2.start();
        T1.join();
        T2.join();
    }
}
