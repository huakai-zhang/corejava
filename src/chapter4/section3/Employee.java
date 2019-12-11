package chapter4.section3;

import chapter5.Person;

import java.time.LocalDate;
import java.util.Objects;

public class Employee extends Person implements Comparable, Cloneable {
    private int id;
    private String name;
    private double salary;
    private LocalDate hireDay;
    private static int nextId;

    /*private int id = assignId();

    private static int assignId() {
        int r = nextId;
        nextId++;
        return r;
    }*/

    private final StringBuilder evaluations = new StringBuilder();

    public Employee() {
    }

    public Employee(String name) {
        this.name = name;
    }

    public Employee(double s) {
        this("Employee #" + nextId, s);
        nextId++;
    }

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public Employee(String name, double salary, int year, int month, int day) {
        this.name = name;
        this.salary = salary;
        hireDay = LocalDate.of(year, month, day);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getDescription() {
        return String.format("an employee with a salary of salary $%.2f", salary);
    }

    @Override
    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public LocalDate getHireDay() {
        return hireDay;
    }

    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100;
        salary += raise;
    }

    @Override
    public boolean equals(Object otherObject) {
        // a quick test to see if the objects are identical
        if (this == otherObject) {
            return true;
        }
        // must return false if the explicit parameter is null
        if (otherObject == null) {
            return false;
        }
        // if the classes don't match, they can't be equal
        if (getClass() != otherObject.getClass()) {
            return false;
        }
        // now we know otherObject is a non-null Employee
        Employee other = (Employee) otherObject;
        // test whether the fields have identical values
        return Objects.equals(name, other.name) && salary == other.salary && Objects.equals(hireDay, other.hireDay);
    }

    @Override
    public int hashCode() {
        return 7 * Objects.hashCode(name)
                + 11 * Double.hashCode(salary)
                + 13 * Objects.hashCode(hireDay);
    }

    @Override
    public String toString() {
        return getClass().getName() + "[name=" + name +
                "salary: " + salary
                + ",hireDay=" + hireDay
                + "]";
    }

    @Override
    public int compareTo(Object otherObject) {
        Employee other = (Employee) otherObject;
        return Double.compare(salary, other.salary);
    }

    @Override
    public Employee clone() throws CloneNotSupportedException {
        return (Employee) super.clone();
    }
}
