package chapter5.section7;

import chapter4.section3.Employee;

import java.util.Random;

public class ReflectiveTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Employee e = new Employee("Carl Cracker", 75000, 1987, 12, 15);
        Class cl = e.getClass();
        System.out.println(cl.getName() + " " + e.getName());
        // chapter4.section2.Employee Carl Cracker
        System.out.println(e.getClass() == Employee.class);

        String className = "java.util.Random";
        Class c = Class.forName(className);
        Object m = Class.forName(className).newInstance();

        Class cl1 = Random.class;
        Class cl2 = int.class;
        Class cl3 = Double[].class;
        System.out.println(cl3.getName());
        // [Ljava.lang.Double;   应用于数组会返回一个奇怪的名字

        Employee l = e.getClass().newInstance();

    }
}
