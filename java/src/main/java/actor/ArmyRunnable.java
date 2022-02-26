package actor;

/**
 * @author Spring Zhang
 * @date 2020/1/14 13:31
 * 军队线程
 */
public class ArmyRunnable implements Runnable {
    /**
     * volatile保住了线程可以正确的读取其他线程写入的值
     */
    volatile boolean keepRunning = true;

    @Override
    public void run() {
        while (keepRunning) {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "进攻对方[" + i + "]");
                // 让出处理器时间，下次该谁进攻不一定
                Thread.yield();
            }
        }
        System.out.println(Thread.currentThread().getName() + "结束了战斗！");
    }
}
