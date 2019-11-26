package chapter6.section2;

import java.util.Arrays;

public class CloneTest {
    public static void main(String[] args) {
        try {
            Employee original = new Employee("John Public", 50000);
            Employee copy = original.clone();
            copy.setName("Jack");
            copy.setHireDay(1998, 10, 01);
            copy.setSalary(40000);
            System.out.println(original.toString());
            System.out.println(copy.toString());
            // Employee[name=John Public,salary: 50000.0,hireDay=Sun Nov 17 17:02:51 CST 2019]
            // Employee[name=Jack,salary: 40000.0,hireDay=Thu Oct 01 00:00:00 CST 1998]

            int[] luckNumbers = { 2, 3, 5, 7, 11, 13};
            int[] cloned = luckNumbers.clone();
            cloned[5] = 12;
            System.out.println(Arrays.toString(luckNumbers));
            System.out.println(Arrays.toString(cloned));
            // [2, 3, 5, 7, 11, 13]
            // [2, 3, 5, 7, 11, 12]
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
