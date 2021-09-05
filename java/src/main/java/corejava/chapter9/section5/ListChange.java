package corejava.chapter9.section5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListChange {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("Amy");
        names.add("Bob");
        names.add("Mack");
        String[] values = names.toArray(new String[0]);
        String[] strings = names.toArray(new String[names.size()]);
        System.out.println(Arrays.toString(values));
        System.out.println(Arrays.toString(strings));
    }
}
