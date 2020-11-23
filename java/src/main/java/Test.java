import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class MyThread extends Thread {
   private int number = 1;
   private Lock lock = new ReentrantLock();
   private Condition condition1 = lock.newCondition();
   private Condition condition2 = lock.newCondition();

   void testA () {
      lock.lock();
      try {
         System.out.println(1);
         System.out.println(2);
         number = 3;
         condition2.signal();
         // number != 1 阻塞
         while (number != 1) {
            condition1.await();
         }
         System.out.println(3);
      } catch (InterruptedException e) {
         e.printStackTrace();
      } finally {
         lock.unlock();
      }
   }

   void testB () {
      lock.lock();
      try {
         // number = 1 阻塞
         while (number != 2 && number != 3) {
            condition2.await();
         }
         System.out.println("B");
         number = 1;
         condition1.signal();
      } catch (InterruptedException e) {
         e.printStackTrace();
      } finally {
         lock.unlock();
      }
   }

   void run1() {
      synchronized (this) {
         while (number < 1000) {
            try {
               //打印是否执行该方法
               System.out.println(Thread.currentThread().getName() + " run1: "+number++);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      }
   }
   void run2() {
      synchronized (this) {
         while (number < 1000) {
            try {
               //打印是否执行该方法
               System.out.println(Thread.currentThread().getName() + " run2: "+number++);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      }
   }

   public Runnable runnable1 = new Runnable() {
      @Override
      public void run() {
         synchronized (this) {
            //同步锁
            while (number < 1000) {
               try {
                  //打印是否执行该方法
                  System.out.println(Thread.currentThread().getName() + " run1: " + number++);
               } catch (Exception e) {
                  e.printStackTrace();
               }
            }
            System.out.println(this);
         }
      }
   };

   public Runnable runnable2 = new Runnable() {
      @Override
      public void run() {
         synchronized (this) {
            //同步锁
            while (number < 1000) {
               try {
                  //打印是否执行该方法
                  System.out.println(Thread.currentThread().getName() + " run2: " + number++);
               } catch (Exception e) {
                  e.printStackTrace();
               }
            }
            System.out.println(this);
         }
      }
   };
}
class MyCallable implements Callable<Integer> {

   @Override
   public Integer call() throws Exception {
      System.out.println("B");
      TimeUnit.SECONDS.sleep(4);
      return 1;
   }
}
public class Test {
   private static int num = 0;

   public static void main(String[] args) throws Exception {
      /*MyThread myThread = new MyThread();
      new Thread(() -> myThread.testB()).start();

      try {
         Thread.sleep(100);
      } catch (InterruptedException e) {
         e.printStackTrace();
      }

      new Thread(() -> myThread.testA()).start();*/

      /*System.out.println(1);
      System.out.println(2);
      FutureTask futureTask = new FutureTask(new MyCallable());
      new Thread(futureTask, "A").start();
      futureTask.get();
      System.out.println(3);*/

      /*Semaphore semaphore = new Semaphore(0);
      Thread a = new Thread(() -> {
         //模拟耗时操作之后初始化变量 num
         try {
            Thread.sleep(1000);
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
         num = 1;
         System.out.println("a计算完成");
         //初始化完参数后释放两个 permit
         semaphore.release(2);
      });

      Thread b = new Thread(() -> {
         try {
            semaphore.acquire();
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
         System.out.println("b:" + num);
      });

      Thread c = new Thread(() -> {
         try {
            semaphore.acquire();
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
         System.out.println("c:" + num);
      });

      a.start();
      b.start();
      c.start();*/
      MyThread myThread = new MyThread();
      new Thread(myThread.runnable1, "A").start();

      new Thread(myThread.runnable2, "B").start();
   }
}
