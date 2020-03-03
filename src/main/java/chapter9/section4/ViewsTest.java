package chapter9.section4;

import chapter4.section3.Employee;

import javax.smartcardio.Card;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class ViewsTest {
    public static void main(String[] args) {
        Employee[] cards = new Employee[52];
        List<Employee> cardList = Arrays.asList(cards);
        //cardList.add(new Employee());
        String[] n = new String[]{"Amy", "Bob", "Carl"};
        List<String> names = Arrays.asList(n);
        names.set(0, "Mack");
        System.out.println(Arrays.toString(n));
        List<String> nameList = Collections.nCopies(10, "Mack");
        System.out.println(names);
        System.out.println(nameList);
        // [Amy, Bob, Carl]
        // [Mack, Mack, Mack, Mack, Mack, Mack, Mack, Mack, Mack, Mack]

        Set<String> deepThoughts = Collections.emptySet();

        List group2 = cardList.subList(10, 20);
    }
}
