package corejava.chapter9.section5;

import java.util.*;

/**
 * @author Spring Zhang
 * @date 2019/12/11 15:06
 */
public class Max {
    public static <T extends Comparable> T max(Collection<T> c) {
        if (c.isEmpty()) {
            throw new NoSuchElementException();
        }
        Iterator<T> iterator = c.iterator();
        T largest = iterator.next();
        while (iterator.hasNext()) {
            T next = iterator.next();
            if (largest.compareTo(next) < 0) {
                largest = next;
            }
        }

        return largest;
    }
}
