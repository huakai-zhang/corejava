package practice.java.init;

class CodeSpring {
    public CodeSpring() {
        System.out.println("Code 构造方法111");
    }
    {
        System.out.println("Code 构造块222");
    }
    static {
        System.out.println("Code 静态代码块333");
    }
}

public class CodeBlock {
    {
        System.out.println("Code 构造块444");
    }
    static {
        System.out.println("Code 静态代码块555");
    }
    public CodeBlock() {
        System.out.println("Code 构造方法666");
    }

    public static void main(String[] args) {
        System.out.println("===================");
        new CodeSpring();
        System.out.println("-------------------");
        new CodeSpring();
        System.out.println("-------------------");
    }
}
