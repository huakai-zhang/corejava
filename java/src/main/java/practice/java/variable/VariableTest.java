package practice.java.variable;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/9/7
 *
 *
 * 局部变量 方法体{}，形参，代码块{}
 * 成员变量 类中方法外
 *  * 类变量 有static修饰
 *  * 实例变量 没有static修饰
 *
 * 修饰符
 *  局部变量 final
 *  成员变量 public,final,static,volatile....
 *
 * 存储位置
 *  局部变量 栈
 *  实例变量 堆
 *  类变量   方法区
 */
public class VariableTest {
    static int s;
    int i;
    int j;
    {
        int i = 1;
        i++;
        j++;
        s++;
    }
    public void test(int j) {
        j++;
        i++;
        s++;
    }

    public static void main(String[] args) {
        VariableTest v1 = new VariableTest();
        VariableTest v2 = new VariableTest();
        v1.test(10);
        v1.test(20);
        v2.test(30);
        System.out.println(v1.i + "," + v1.j + "," + v1.s);
        System.out.println(v2.i + "," + v2.j + "," + v2.s);
    }
}
