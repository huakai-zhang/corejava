import java.util.Scanner;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/8/7
 */
public class Test {

   public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
       System.out.println("请输入需要递归调用的字符：");
       String s = scanner.next();
       substring(s);
   }

   static void substring(String s) {
       String i = s.substring(s.length() - 1, s.length());
       System.out.print(i);
       if (s.length() - 1 > 0) {
           substring(s.substring(0, s.length() - 1));
       }
   }

}
