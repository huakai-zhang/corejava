package actor;

/**
 * @author Spring Zhang
 * @date 2020/1/14 14:27
 */
public class KeyPersonThread extends Thread {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "开始了战斗！");
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "左突右杀，击杀隋军！");
        }
        System.out.println(Thread.currentThread().getName() + "结束了战斗！");
    }
}
