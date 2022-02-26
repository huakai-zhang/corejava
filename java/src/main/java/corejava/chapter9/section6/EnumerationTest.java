package corejava.chapter9.section6;

import corejava.chapter4.section3.Employee;

import java.util.Enumeration;
import java.util.Hashtable;

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
