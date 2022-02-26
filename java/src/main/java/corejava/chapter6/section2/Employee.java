package corejava.chapter6.section2;

import java.util.Date;
import java.util.GregorianCalendar;

public class Employee implements Cloneable {

    private String name;
    private double salary;
    private Date hireDay;

    public Employee() {
    }

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
        hireDay = new Date();
    }

    @Override
    public Employee clone() throws CloneNotSupportedException {
        Employee cloned = (Employee) super.clone();
        cloned.hireDay = (Date) hireDay.clone();
        return cloned;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setHireDay(int year, int mouth, int day) {
        // 如果直接这样设置this.hireDay = hireDay，传入的是一个新的引用的话，无法测试浅拷贝的问题
        Date newHireDay = new GregorianCalendar(year, mouth - 1, day).getTime();
        // 只是改变了值，为改变引用
        hireDay.setTime(newHireDay.getTime());
    }

    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100;
        salary += raise;
    }

    @Override
    public String toString() {
        return getClass().getName() + "[name=" + name +
                ",salary: " + salary
                + ",hireDay=" + hireDay
                + "]";
    }

}
