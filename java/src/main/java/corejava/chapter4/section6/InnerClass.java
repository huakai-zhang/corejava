package corejava.chapter4.section6;

public class InnerClass extends SuperClass {
    {
        System.out.println("innerClass instance block");
    }
    public InnerClass() {
        super(0);
        System.out.println("innerClass()");
    }

    public InnerClass(int t) {
        //super(t);
        System.out.println("superClass (int)");
    }

    static {
        System.out.println("innerClass static block");
    }

    public static void main(String[] args) {
        new InnerClass();
    }
}
