package practice.java;

/**
 * @author 春阳
 * @date 2021-03-15 18:13
 */
public class ToBinary {
    public static void main(String[] args) {
        int n = -10;
        for (int i = 31; i >= 0; i--) {
            if ((n & (1 << i)) != 0) {
                System.out.print("1");
            } else {
                System.out.print("0");
            }
        }
    }
}
