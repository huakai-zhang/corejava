package juc.core;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/9/15
 */
public class ShareData {
    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int j = 0; j < 100; j++) {
                    addCount();
                }
                System.out.print(count + "  ");
            }).start();
        }

        Thread.sleep(3000);
        System.out.println("count= " + count);
    }

    private synchronized static void addCount() {
        count++;
    }
}
