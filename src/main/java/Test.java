

/**
 * @author Spring Zhang
 * @date 2019/12/16 13:20
 */
public class Test {
    public static void main(String[] args) {
        float f = 3.75F;
        int fb = Float.floatToIntBits(f);
        System.out.println(Integer.toBinaryString(fb));

        double a = 0.2;
        long b = Double.doubleToLongBits(a);
        System.out.println(Long.toBinaryString(b));

        float af[][] = new float[6][6];
        float []bf[] = new float[6][6];
        // float cf[][] = new float[][6];
        float [][]ef = new float[6][6];
        float [][]ff = new float[6][];
    }

    public static int val() {
        int num = 5;
        try {
            num = num / 0;
        } catch (Exception e) {
            num = 10;
        } finally {
            num = 15;
        }
        return num;
    }
}
