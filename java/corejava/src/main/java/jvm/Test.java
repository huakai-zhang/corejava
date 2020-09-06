package jvm;

public class Test {
    public static void main(String[] args) {
        m1();
    }

    public static void m1() {
        m1();
    }
}
// Exception in thread "main" java.lang.StackOverflowError
