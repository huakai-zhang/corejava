import com.spring.model.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test {

   public static void main(String[] args) {
      User u1 = new User();
      u1.setName("Spring");

      User u2 = u1;

      User u3 = new User();
      u3.setName("Xiaoxiao");

      u2.setName("Yang");

      u2 = u3;
      System.out.println(u1.getName());
      System.out.println(u2.getName());
      System.out.println(u3.getName());
   }
}
