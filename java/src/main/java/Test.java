import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test {

   public static void main(String[] args) {
      List<String> list = new ArrayList<>();
      list.add("a");
      list.add("a");
      System.out.println(list.size());
      Set<String> set = new HashSet<>();
      set.add("a");
      set.add("a");
      System.out.println(set.size());
   }
}
