package chapter5;

import chapter4.section3.Employee;

public class PersonTest {
    public static void main(String[] args) {
        Person[] people = new Person[2];

        // fill the people array with Student and Employee objects
        people[0] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        people[1] = new Student("Mari a Morris", "computer science");

        // print out names and descriptions of all Person objects
        for (Person p : people) {
            //System.out.println(p.getName() + " , " + p.getDescription());
        }
    }
}