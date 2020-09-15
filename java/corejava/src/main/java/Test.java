import java.util.Arrays;
import java.util.Iterator;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/8/7
 */
public class Test {

   ThreadLocal<Long> longLocal = new ThreadLocal<>();
   public void set() {

         longLocal.set(Thread.currentThread().getId());


   }
   public long getLong() {

         return longLocal.get();

   }

   public static void main(String[] args) {

      Test test = new Test();
      test.set();
      //注意:没有set之前，直接get，报null异常了
      System.out.println("-------threadLocal value-------" + test.getLong());
   }

   public static  void bubbleSort(int[] list) {
      for (int i = 0; i < list.length - 1; i++) {
         for (int j = list.length - 1; j > i; j--) {
            if (list[j - 1] > list[j]) {
               int temp = list[j - 1];
               list[j - 1] = list[j];
               list[j] = temp;
            }
         }
      }
   }

   public static void quickSort(int[] list, int leftIndex, int rightIndex) {
      if (leftIndex > rightIndex) {
         return;
      }

      int left = leftIndex;
      int right = rightIndex;
      int base = list[left];

      while (left != right) {
         while (left < right && list[right] >= base) {
            right--;
         }

         while (left < right && list[left] <= base) {
            left++;
         }

         if (left < right) {
            int temp = list[left];
            list[left] = list[right];
            list[right] = temp;
         }
      }

      list[leftIndex] = list[left];
      list[left] = base;

      quickSort(list, leftIndex, left - 1);
      quickSort(list, left + 1, rightIndex);
   }

   public static int select(int[] list, int key) {
      int i = 0;
      int j = list.length - 1;
      while (i <= j) {
         int mid = (i + j) / 2;
         if (list[mid] > key) {
            j = mid - 1;
         } else if (list[mid] < key) {
            i = mid + 1;
         } else {
            return mid;
         }
      }
      return - (i + 1);
   }
}
