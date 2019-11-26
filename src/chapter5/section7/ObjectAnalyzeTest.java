package chapter5.section7;

import chapter4.section3.Employee;

import java.lang.reflect.Field;

public class ObjectAnalyzeTest {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Employee obj  = new Employee("Carl Cracker", 75000, 1987, 12, 15);
        Class cl = obj .getClass();
        Field f = cl.getDeclaredField("name");
        f.setAccessible(true);
        Object v = f.get(obj );
        System.out.println(v);
    }
}
