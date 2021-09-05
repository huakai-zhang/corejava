package corejava.chapter9.section3;

import corejava.chapter4.section3.Employee;

import java.util.HashMap;
import java.util.Map;

public class MapForEachTest {
    public static void main(String[] args) {
        Map<String, Employee> staff = new HashMap<>();
        staff.put("144-25-5464", new Employee("Amy Lee"));
        staff. put("567-24-2546", new Employee("Harry Hacker"));
        staff. put("157-62-7935", new Employee("Cary Cooper"));
        staff.put("456-62-5527", new Employee("Francesca Cruz"));
        staff.forEach((k, v) -> {
            System.out.println(k);
        });
    }
}
