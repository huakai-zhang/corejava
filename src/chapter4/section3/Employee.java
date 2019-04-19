package chapter4.section3;

import chapter5.Person;

import java.time.LocalDate;
import java.util.Objects;

import static javafx.scene.input.KeyCode.T;

public class Employee extends Person {
    private String name;
    private double salary;
    private LocalDate hireDay;

    public Employee(String name, double salary, int year, int month, int day) {
        this.name = name;
        this.salary = salary;
        hireDay = LocalDate.of(year, month, day);
    }

    @Override
    public String getDescription() {
        return String.format("an employee with a salary of salary $%.2f", salary);
    }

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
        return name.equals(other.name) && salary == other.salary && hireDay.equals(other.hireDay);
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
}
