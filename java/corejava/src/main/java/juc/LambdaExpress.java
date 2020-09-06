package juc;

@FunctionalInterface
interface Foo {
    int add(int x, int y);

    default int div(int x, int y) {
        System.out.println("div");
        return x / y;
    }
    default int mul(int x, int y) {
        System.out.println("mul");
        return x * y;
    }
    public static int sub (int x, int y) {
        System.out.println("sub");
        return x - y;
    }
}

/**
 * 拷贝小括号，写死右箭头，落地大括号
 * FunctionInterface
 * default
 */
public class LambdaExpress {
    public static void main(String[] args) {
        Foo foo = (x, y) -> {
            System.out.println("add");
            return x + y;
        };
        System.out.println(foo.add(10, 5));
        System.out.println(foo.div(10,5));
        System.out.println(foo.mul(10,5));
        System.out.println(Foo.sub(10,5));
    }
}
