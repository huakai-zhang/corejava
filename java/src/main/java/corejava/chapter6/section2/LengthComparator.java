package corejava.chapter6.section2;

import java.util.Arrays;
import java.util.Comparator;

public class LengthComparator implements Comparator<String> {

    @Override
    public int compare(String first, String second) {
        return first.length() - second.length();
    }

    public static void main(String[] args) {
        String[] friends = { "Peter", "Paul", "Mary" };
        Arrays.sort(friends, new LengthComparator());
        System.out.println(Arrays.toString(friends));
        // [Paul, Mary, Peter]
    }
}
