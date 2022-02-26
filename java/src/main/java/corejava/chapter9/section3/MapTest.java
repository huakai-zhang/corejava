package corejava.chapter9.section3;

import corejava.chapter4.section3.Employee;

import java.util.HashMap;
import java.util.Map;

public class MapTest {
    public static void main(String[] args) {
        Map<String, Integer> scores = new HashMap<>();
       /* System.out.println(scores.get("a"));
        System.out.println(scores.getOrDefault("a", 0));*/
        scores.putIfAbsent("a", 0);
        scores.put("a", scores.get("a") + 1);
        // null
        // 0
        System.out.println(scores.put("a", 0));
        System.out.println(scores.put("a", 1));
        // null
        // 0
        scores.forEach((k, v) -> System.out.println("key=" + k + ", value=" + v));

        Map<String, Employee> staff = new HashMap<>();
        staff.put("144-25-5464", new Employee("Amy Lee"));
        staff. put("567-24-2546", new Employee("Harry Hacker"));
        staff. put("157-62-7935", new Employee("Cary Cooper"));
        staff.put("456-62-5527", new Employee("Francesca Cruz"));

        System.out.println(staff);

        staff.remove("567-24-2546");

        staff.put("456-62-5527", new Employee("Francesca Miller"));

        System.out.println(staff.get("157-62-7935"));

        staff.forEach((k, v) -> System.out.println("key=" + k + ", value=" + v));
    }
}
