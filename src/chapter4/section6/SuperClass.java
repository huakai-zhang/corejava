package chapter4.section6;

public class SuperClass {
    {
        System.out.println("superClass instance block");
    }

    public SuperClass() {
        System.out.println("superClass()");
    }

    public SuperClass(int i) {
        System.out.println("superClass(int)");
    }

    static {
        System.out.println("superClass static block");
    }
}
