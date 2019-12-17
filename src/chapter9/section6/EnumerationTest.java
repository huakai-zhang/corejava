package chapter9.section6;

import chapter4.section3.Employee;

import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

public class EnumerationTest {
    public static void main(String[] args) {
        Hashtable<String, Employee> staff = new Hashtable<>();
        staff.put("123", new Employee("Amy"));
        staff.put("456", new Employee("Bob"));
        staff.put("789", new Employee("Mack"));
        Enumeration<Employee> e = staff.elements();
        while (e.hasMoreElements()) {
            Employee employee = e.nextElement();
            System.out.println(employee.getName());
        }

        /*List<InputStream> streams = ...;
        SequenceInputStream in = new SequenceInputStream(Collections.enumeration(streams));*/
    }
}
