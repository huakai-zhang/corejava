package corejava.chapter4.section6;

import corejava.chapter4.section3.Employee;

public class OverLoadingTest {
    public static void main(String[] args) {
        StringBuilder messages = new StringBuilder();
        StringBuilder todoList = new StringBuilder("To do:\n");



        Employee e1 = new Employee();
        Employee e2 = new Employee();
        System.out.println(e1.getId());
        System.out.println(e2.getId());
    }
}
