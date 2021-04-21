import java.util.concurrent.TimeUnit;

public class Test {

   private static final ThreadLocal<Long> THREAD_LOCAL = ThreadLocal.withInitial(System::currentTimeMillis);

   public static void begin() {
      THREAD_LOCAL.set(System.currentTimeMillis());
   }

   public static long end() {
      return System.currentTimeMillis() - THREAD_LOCAL.get();
   }

   public static void main(String[] args) throws InterruptedException {
      Test.begin();
      TimeUnit.SECONDS.sleep(1);
      System.out.println("Cost: " + Test.end() + " mills");
   }

}