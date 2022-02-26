package corejava.chapter5.section3;

import corejava.chapter4.section3.Employee;

import java.util.ArrayList;

public class ArrayTets {
    public static void main(String[] args) {
        @SuppressWarnings("unckecked")
        ArrayList<Employee> staff = new ArrayList<>(100);
        staff.add(new Employee("Carl Cracker", 75000, 1987, 12, 15));
        staff.add(new Employee("Harry Hacker", 50000, 1989, 10, 1));
        staff.trimToSize();
        staff.add(new Employee("Tony Tester", 40000, 1990, 3, 15));
        System.out.println(staff.size());

    }
}
