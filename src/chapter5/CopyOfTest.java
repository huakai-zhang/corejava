package chapter5;

import java.lang.reflect.Array;
import java.util.Arrays;

public class CopyOfTest {
    public static void main(String[] args) {
        int[] a = {1, 2, 3};
        a = (int[]) goodCopyOf(a, 10);
        System.out.println(Arrays.toString(a));

        String[] b = {"TonT", "Dick", "Harry"};
        b = (String[]) goodCopyOf(b, 10);
        System.out.println(Arrays.toString(b));

        System.out.println("The following call will generate an exception. ");
        //b = (String[]) badCopyOf(b, 10);
    }

    /**
     * 28 * This method attempts to grow an array by allocating a new array and copying all elements.
     * 29 * @param a the array to grow
     * 30 * @param newLength the new length
     * 31 * return a larger array that contains all elements of a. However, the returned array has
     * 32 * type ObjectQ , not the same type as a
     * 33
     */
    public static Object[] badCopyOf(Object[] a, int newLength) // not useful
    {
        Object[] newArray = new Object[newLength];
        System.arraycopy(a, 0, newArray, 0, Math.min(a.length, newLength));
        return newArray;
    }

    /**
     * 42 * This method grows an array by allocating a new array of the same type and
     * 43 * copying all elements.
     * 44 * @param a the array to grow. This can be an object array or a primitive
     * 4s * type array
     * 46 * return a larger array that contains all elements of a.
     * 47
     */
    public static Object goodCopyOf(Object a, int newLength) {
        Class cl = a.getClass();
        if (!cl.isArray()) {
            return null;
        }

        Class componentType = cl.getComponentType();
        int length = Array.getLength(a);
        Object newArray = Array.newInstance(componentType, newLength);
        System.arraycopy(a, 0, newArray, 0, Math.min(length, newLength));
        return newArray;
    }
}
