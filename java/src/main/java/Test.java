
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Test {

   public static void main(String[] args) throws InterruptedException {
      Thread countThread = new Thread(new Runner(), "CountThread");
      countThread.start();

      TimeUnit.SECONDS.sleep(1);

      countThread.interrupt();

      Runner runner = new Runner();

      countThread = new Thread(runner, "CountThread");
      countThread.start();

      TimeUnit.SECONDS.sleep(1);

      runner.cancel();
   }

}
class Runner implements Runnable{
   private long i;

   private volatile boolean on = true;

   @Override
   public void run() {
      while (on && !Thread.currentThread().isInterrupted()) {
         i++;
      }
      System.out.println("Count i = " + i);
   }

   public void cancel() {
      on = false;
   }
}