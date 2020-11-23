package chapter5.section7;

public class ExceptionTest {
    public static void main(String[] args) {
        try {
            String name = "java.util.Random";
            Class cl = Class.forName(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
