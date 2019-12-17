import java.util.*;

/**
 * @author Spring Zhang
 * @date 2019/12/16 13:20
 */
public class Test {
    public static void main(String[] args) {
        List<String> staff = new LinkedList<>();
        staff.add("Amy");
        staff.add("Bob");
        staff.add("Carl");
        for (int i = 0; i < staff.size(); i++) {
            System.out.println(staff.get(i));
        }


        Map<String, Integer> map = new HashMap<>();
        map.put("", 1);
        map.put(null, 2);
        map.put("Amy", 3);
        Set<String> keys = map.keySet();
        for(String key : keys) {
            System.out.println(key);
        }

        Collection<Integer> values = map.values();
        for(Integer value : values) {
            System.out.println(value);
        }

        Set<Map.Entry<String, Integer>> maps = map.entrySet();
        maps.forEach((k) -> System.out.println(k.getKey() + ":" + k.getValue()));

        map.forEach((k, v) -> System.out.println(k + "=" + v));

        /*List<String> names = Arrays.asList("Amy", "Bob", "Carl");
        names.set(1, "aa");*/

        List<String> names = new ArrayList<>();
        names.add("Amy");
        names.add("Bob");
        names.add("Spring");
        names.add("Zhang");
        names.add("Carl");
        List group = names.subList(2, 4);
        group.add("Haha");
        System.out.println(names);
        System.out.println(group);
        group.clear();
        System.out.println(names);
        System.out.println(group);
    }
}
