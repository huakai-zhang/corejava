package corejava.chapter7.section6;

import java.util.Random;
import java.util.logging.Logger;

/**
 * @author Spring Zhang
 * @date 2019/11/27 13:34
 */
public class RandomTest {
    public static void main(String[] args) {
        Random generator = new Random() {
          @Override
          public double nextDouble() {
              double result = super.nextDouble();
              Logger.getGlobal().info("nextDouble: " + result);
              return result;
          }
        };
    }
}
