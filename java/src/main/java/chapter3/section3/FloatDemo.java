package chapter3.section3;

/**
 * @author 春阳
 * @date 2021-03-16 12:08
 */
public class FloatDemo {

    public static void main(String[] args) {
        float f = 3.75F;
        int fb = Float.floatToIntBits(f);
        System.out.println(Integer.toBinaryString(fb));
        double a = 0.2;
        long b = Double.doubleToLongBits(a);
        System.out.println(Long.toBinaryString(b));
    }

}
