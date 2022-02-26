package practice.java.init;

/**
 * 类初始化过程，只执行一次<cinit>
 *      父类依次执行所有域静态初始化语句和静态初始化块
 *      依次执行所有域静态初始化语句和静态初始化块

 * 实例初始化过程<init>
 *      父类所有数据域被初始化默认值
 *      父类按照在类声明中出现的次序，依次执行父级所有域初始化语句和初始化块
 *      实例初始化构造器中调用了父级构造器，执行对应父级构造器，未调用则执行默认父级构造器
 *      所有数据域被初始化默认值
 *      按照在类声明中出现的次序，依次执行所有域初始化语句和初始化块
 *      如果构造器第一行调用了第二个构造器，则执行第二个构造器主体
 *      执行这个构造器主体
 *
 *
 * 不会被重写的方法：
 * final
 * static
 * 父类private
 */
public class Son extends Father {
    private int i = test();
    private static int j = method();

    static {
        System.out.print("(6)");
    }
    Son () {
        // super()写不写都在，在子类构造器中一定会调用父类构造器
        System.out.print("(7)");
    }
    {
        System.out.print("(8)");
    }
    @Override
    public int test() {
        System.out.print("(9)");
        return 1;
    }
    public static int method() {
        System.out.print("(10)");
        return 1;
    }

    public static void main(String[] args) {
        Son s1 = new Son();
        System.out.println();
        Son s2 = new Son();
    }
    // 5 1 10 6 3 2 8 7
}
