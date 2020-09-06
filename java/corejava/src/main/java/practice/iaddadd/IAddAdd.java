package practice.iaddadd;

public class IAddAdd {
    public static void main(String[] args) {
        int i = 1;
        i = i++;
        int j = i++;
        int k = i + ++i * i++;
        System.out.println("i:" + i);
        System.out.println("j:" + j);
        System.out.println("k:" + k);
    }
    /**
     * 0: iconst_1 将 int 型 0 推送至栈顶
     * 1: istore_1 将栈顶int型存入第一个本地变量
     * 2: iload_1  将第一个本地变量推送至栈顶
     * 3: iinc          1, 1   将指定int型变量增加指定值
     * 6: istore_1 将栈顶int型存入第一个本地变量
     */
}
