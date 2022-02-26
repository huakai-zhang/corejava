package corejava.chapter9.section5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Spring Zhang
 * @date 2019/12/11 17:24
 */
public class SortTest {
    public static void main(String[] args) {
        List<String> staff = new ArrayList<>();
        staff.add("Mack");
        staff.add("Amy");
        staff.add("Bobby");

        Collections.sort(staff);
        System.out.println(staff);

        staff.sort(Collections.reverseOrder());
        System.out.println(staff);

        staff.sort(Comparator.reverseOrder());
        System.out.println(staff);

        staff.sort(Comparator.comparingInt(String::length).reversed());
        System.out.println(staff);
        // [Amy, Bobby, Mack]
        // [Mack, Bobby, Amy]
        // [Mack, Bobby, Amy]
        // [Bobby, Mack, Amy]
    }
}
