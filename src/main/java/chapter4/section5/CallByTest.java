package chapter4.section5;

import chapter4.section3.Employee;

public class CallByTest {
    public static void main(String[] args) {
        String s = "Jack";
        tripleString(s);
        System.out.println(s);

        StringBuffer ss = new StringBuffer("Tom");
        tripleStringBuffer(ss);
        System.out.println(ss.toString());

        double percent = 10;
        tripleValue(percent);
        System.out.println(percent);

        Employee harry = new Employee("Carl Cracker", 75000, 1987, 12, 15);
        tripleSalary(harry);
        System.out.println(harry.getSalary());

        Employee a = new Employee("Carl Cracker", 75000, 1987, 12, 15);
        Employee b = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        swap(a, b);
        System.out.println(a.getName());
        System.out.println(b.getName());
    }
    static void tripleString(String s) {
        s = s + " Hello";
    }

    static void tripleStringBuffer(StringBuffer s) {
        s.append(" Hello");
    }

    static void tripleValue(double x) {
        x = 3 * x;
    }
    static void tripleSalary(Employee x) {
        x.raiseSalary(200);
    }
    static void swap(Employee x, Employee y) {
        Employee temp = x;
        x = y;
        y = temp;
    }
}
