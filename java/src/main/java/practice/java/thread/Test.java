package practice.java.thread;

import java.util.concurrent.*;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/9/7
 */
class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("********come in");
        try { TimeUnit.SECONDS.sleep(4); } catch (InterruptedException e) { e.printStackTrace(); }
        return 1024;
    }
}

public class Test {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        FutureTask futureTask = new FutureTask(new MyThread());
        new Thread(futureTask, "A").start();
        System.out.println("计算完成：");
        System.out.println(futureTask.get());
    }
}
