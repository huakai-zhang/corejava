package corejava.chapter3.section6;

public class SubstringTest {
    public static void main(String[] args) {
        String greeting = "Hello";
        String s = greeting.substring(0,3);
        System.out.println(s);

        greeting = greeting.substring(0, 3) + "p!";
        System.out.println(greeting);
    }
}
