
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Test {


   public static void main(String[] args) {
      ThreadPoolExecutor poolExecutor=new ThreadPoolExecutor(5, 10,
              1,
              TimeUnit.MINUTES,
              new LinkedBlockingDeque<Runnable>(2)
      );

      List<Runnable> rlist=new ArrayList<>();
      for(int i=0;i<20;i++){
         rlist.add(new RunnableTest(poolExecutor));
      }
      for(int i=0;i<20;i++){
         poolExecutor.execute(rlist.get(1));
      }
   }

}
class RunnableTest implements Runnable{

   ThreadPoolExecutor poolExecutor;

   public RunnableTest(  ThreadPoolExecutor poolExecutor){
      this.poolExecutor=poolExecutor;
   }

   @Override
   public void run() {
      int threadSize=this.poolExecutor.getActiveCount();
      int queueCurrentSize=this.poolExecutor.getQueue().size();
      System.out.println(Thread.currentThread().getName()+":执行开始："+"当前线程数："+threadSize+"当前队列大小："+queueCurrentSize);
      try {
         Thread.sleep(1000);
      } catch (InterruptedException e) {
         e.printStackTrace();
      }
   }
}