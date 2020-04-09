package chapter5;

import java.lang.reflect.Method;

public class MethodTableTest {
    public static void main(String[] args) throws Exception {
        // get method pointers to the square and sqrt methods
        Method square = MethodTableTest.class.getMethod("square", double.class);
        Method sqrt = Math.class.getMethod("sqrt", double.class);

        // print tables of x- and y-values

        printTable(1, 10, 10, square);
        printTable(1, 10, 10, sqrt);
    }

    /**
     * 2 s * Returns the square of a number
     * 26 * @param x a number
     * 27 * ©return x squared
     * 28
     */
    public static double square(double x) {
        return x * x;
    }

    /**
     * 35 * Prints a table with x- and y-values for a method
     * 36 * @param from the lower bound for the x-values
     * 37 * @param to the upper bound for the x-values
     * 38
     *
     * @param n the number of rows in the table
     *          39 * @param f a method with a double parameter and double return value
     *          40
     */
    public static void printTable(double from, double to, int n, Method f) {
        // print out the method as table header
        System.out.println(f);

        double dx = (to - from) / (n - 1);
        for (double x = from; x <= to; x += dx) {
            try {
                double y = (Double) f.invoke(null, x);
                System.out.printf("%10.4f | %10.4f%n", x, y);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
