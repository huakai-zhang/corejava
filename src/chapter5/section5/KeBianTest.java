package chapter5.section5;

public class KeBianTest {
    public static void main(String[] args) {
        System.out.println(max(3.1, 40.4, -5));
        System.out.printf("%d %s", new Object[] { new Integer(1), "widgets"});
    }

    public static double max(double... values) {
        double largest = Double.NEGATIVE_INFINITY;
        for (double v : values) {
            if (v > largest) {
                largest = v;
            }
        }
        return largest;
    }
}
