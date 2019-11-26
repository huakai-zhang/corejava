package chapter6;

import chapter4.section3.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import static java.util.Comparator.*;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.toString();
        Employee[] employees = {};
        Arrays.sort(employees, comparing(Employee::getName, nullsFirst(naturalOrder())));
    }
}
