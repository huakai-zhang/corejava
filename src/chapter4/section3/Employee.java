package chapter4.section3;

import chapter5.Person;

import java.time.LocalDate;

public class Employee extends Person {
    private double salary;
    private LocalDate hireDay;

    public Employee(String name , double salary , int year, int month, int day) {
        super(name);
        this.salary = salary;
        hireDay = LocalDate .of(year, month , day);
    }

    @Override
    public String getDescription() {
        return String.format("an employee with a salary of salary $%.2f", salary);
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
}
