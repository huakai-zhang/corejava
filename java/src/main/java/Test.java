import com.spring.design.proxy.proxy.jdk.Person;
import com.spring.design.proxy.proxy.jdk.XiaoXingxing;

import java.lang.reflect.Proxy;

public class Test {


   public static void main(String[] args) {
      System.out.println(XiaoXingxing.class.isInterface());
      System.out.println(Proxy.isProxyClass(XiaoXingxing.class));
   }

}
