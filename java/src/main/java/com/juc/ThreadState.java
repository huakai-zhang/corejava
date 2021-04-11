package com.juc;

import java.util.concurrent.TimeUnit;

/**
 * @author 花开不合阳春暮
 * @date 2021/4/10 下午2:47
 */
public class ThreadState {

    public static void main(String[] args) {
        new Thread(new TimeWaiting(), "TimeWaitingThread").start();
        new Thread(new Waiting(), "WaitingThread").start();
        new Thread(new Blocked(), "BlockedThread-1").start();
        new Thread(new Blocked(), "BlockedThread-2").start();
    }

    static class TimeWaiting implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Waiting implements Runnable {

        @Override
        public void run() {
            while (true) {
                synchronized(Waiting.class) {
                    try {
                        Waiting.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class Blocked implements Runnable {

        @Override
        public void run() {
            synchronized(Blocked.class) {
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
// "BlockedThread-2" #12 prio=5 os_prio=31 tid=0x00007fd4108e4800 nid=0x5503 waiting for monitor entry [0x0000700010555000]
// java.lang.Thread.State: BLOCKED (on object monitor)

// "BlockedThread-1" #11 prio=5 os_prio=31 tid=0x00007fd4108e4000 nid=0x4203 waiting on condition [0x0000700010452000]
// java.lang.Thread.State: TIMED_WAITING (sleeping)

// "WaitingThread" #10 prio=5 os_prio=31 tid=0x00007fd4108e3000 nid=0x3f03 in Object.wait() [0x000070001034f000]
// java.lang.Thread.State: WAITING (on object monitor)

// "TimeWaitingThread" #9 prio=5 os_prio=31 tid=0x00007fd411080800 nid=0x4303 waiting on condition [0x000070001024c000]
// java.lang.Thread.State: TIMED_WAITING (sleeping)