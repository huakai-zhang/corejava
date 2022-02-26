package actor;

/**
 * @author Spring Zhang
 * @date 2020/1/14 14:16
 */
public class Stage extends Thread {

    @Override
    public void run() {
        System.out.println("欢迎观看隋唐演义！");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("大幕徐徐拉开！");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("话说隋朝末年，隋军与农民军杀得昏天黑地...");

        ArmyRunnable armyTaskOfSuiDynasty = new ArmyRunnable();
        ArmyRunnable armyTaskOfRevolt = new ArmyRunnable();
        // 使用Runnable接口创建线程
        Thread armyOfSuiDynasty = new Thread(armyTaskOfSuiDynasty, "隋军");
        Thread armyOfRevolt = new Thread(armyTaskOfRevolt, "农民起义军");

        // 启动线程，让军队开始作战
        armyOfSuiDynasty.start();
        armyOfRevolt.start();

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("正当双方激战正酣，半路杀出个程咬金！");

        Thread mrCheng = new KeyPersonThread();
        mrCheng.setName("程咬金");
        System.out.println("程咬金的理想就是结束战争，使百姓安居乐业！");

        // 军队停止作战
        /*armyTaskOfSuiDynasty.keepRunning = false;
        armyTaskOfRevolt.keepRunning = false;*/
        armyOfSuiDynasty.stop();
        armyOfRevolt.stop();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mrCheng.start();
        // 万众瞩目，所有线程等待程线程完成历史使命
        try {
            mrCheng.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("战争结束，人民安居乐业！");
        System.out.println("谢谢观看！");
    }

    public static void main(String[] args) {
        new Stage().start();
    }
}
