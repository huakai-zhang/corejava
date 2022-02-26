package corejava.chapter3.section5;

public class FloorModeTest {
    public static void main(String[] args) {
        int n = -13;
        System.out.println(n % 2);
        System.out.println(Math.floorMod(n, 2));
        // -1
        // 1
    }
}
