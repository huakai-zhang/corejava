package chapter9.section4;

import java.util.ArrayList;
import java.util.List;

public class SubrangeViewTest {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("Amy");
        names.add("Bob");
        names.add("Yoyo");
        names.add("Claire");
        names.add("Carl");
        List group = names.subList(2, 4);
        group.add("Haha");
        System.out.println(names);
        System.out.println(group);
        group.clear();
        System.out.println(names);
        System.out.println(group);
        // [Amy, Bob, Yoyo, Claire, Haha, Carl]
        // [Yoyo, Claire, Haha]
        // [Amy, Bob, Carl]
        // []
    }
}
