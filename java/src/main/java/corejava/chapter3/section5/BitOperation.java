package corejava.chapter3.section5;

public class BitOperation {
    public static void main(String[] args) {
        int n = 23;
        int fourthBitFromRight = (n & 0b1000) / 0b1000;
        int fourthBitFromRight1 = (n & (1 << 3)) >> 3;
        System.out.println(fourthBitFromRight);
        System.out.println(fourthBitFromRight1);
    }
}
