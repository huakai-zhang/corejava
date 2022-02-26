package corejava.chapter3.section5;

public class DivisionOperation {
    public static void main(String[] args) {
        int i = 1;
        double s = 1.2;
        // 整数被0除将会产生一个异常，而浮点数被0除将会得到无穷大或NaN结果
        // System.out.println(i / 0);
        System.out.println(s / 0);
    }
}
