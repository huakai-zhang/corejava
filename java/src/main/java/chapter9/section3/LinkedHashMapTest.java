package chapter9.section3;

import chapter4.section3.Employee;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapTest {
    public static void main(String[] args) {
        Map<String, Employee> staff = new LinkedHashMap<>();
        staff.put("144-25-5464", new Employee("Amy Lee"));
        staff. put("567-24-2546", new Employee("Harry Hacker"));
        staff. put("157-62-7935", new Employee("Cary Cooper"));
        staff.put("456-62-5527", new Employee("Francesca Cruz"));
        staff.forEach((k, v) -> {
            System.out.println(k);
            System.out.println(v.getName());
        });
        // 144-25-5464
        // 567-24-2546
        // 157-62-7935
        // 456-62-5527

        Map<String, Employee> staffs = new LinkedHashMap<>(4, 0.75f, true);
        staffs.put("144-25-5464", new Employee("Amy Lee"));
        staffs.put("567-24-2546", new Employee("Harry Hacker"));
        staffs.put("157-62-7935", new Employee("Cary Cooper"));
        staffs.put("456-62-5527", new Employee("Francesca Cruz"));
        staffs.get("157-62-7935");
        staffs.forEach((k, v) -> System.out.println(k));
        // 144-25-5464
        // 567-24-2546
        // 456-62-5527
        // 157-62-7935

        /*Map<K, V> cache = new LinkedHashMap<>(128, 0.75F, true){
            protected boolean removeEldestEntry() {
                return size() > 100;
            }
        }();*/
    }
}
