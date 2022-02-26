package corejava.chapter3.section6;

public class StringEqualTest {
    public static final int vacationDays = 1;

    public static void main(String[] args) {
        String s1 = new String("A");
        String s2 = new String("A");
        System.out.println(s1.equals(s2));
        System.out.println(s1 == s2);

        String s3 = "B";
        String s4 = "B";
        System.out.println(s3.equals(s4));
        System.out.println(s3 == s4);

        String greeting = "Hello";
        System.out.println(greeting == "Hello");
        System.out.println(greeting.substring(0, 3) == "Hel");
        // true
        // false
        // true
        // true
        // true
        // false
    }
}
