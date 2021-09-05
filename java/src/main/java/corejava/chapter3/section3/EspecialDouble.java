package corejava.chapter3.section3;

public class EspecialDouble {
    public static void main(String[] args) {
        // 不能检测一个特定值是否等于Double.NaN，因为Double.NaN == Double.NaN，结果为false
        double x = Double.NaN;
        System.out.println(x == Double.NaN);
        // 使用Double.isNaN可判断
        System.out.println(Double.isNaN(x));
    }
}
