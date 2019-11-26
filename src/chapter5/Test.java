package chapter5;

import chapter4.section3.Employee;

import java.util.ArrayList;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) throws Exception {
        Employee harry = new Employee("Harry Hacker", 35000, 1989, 1, 19);
        ArrayList<Integer> squares = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            squares.add(i * i);
        }
        System.out.println(new ObjectAnalyzer().toString(harry));
        Person p = new Student("Wu", "Economics");
        System.out.println(p instanceof Object);
        System.out.println(p instanceof Student);
    }
}
