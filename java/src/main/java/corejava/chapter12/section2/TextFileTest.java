package corejava.chapter12.section2;

import corejava.chapter4.section3.Employee;

import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * @author Spring Zhang
 * @date 2019/12/20 13:16
 */
public class TextFileTest {

    public static void main(String[] args) throws IOException {
        Employee[] staff = new Employee[3];
        staff[0] = new Employee("Carl Cracker", 75000, 2019, 12, 20);
        staff[1] = new Employee("Harry Hacker", 50000, 2019, 12, 19);
        staff[2] = new Employee("Tony Tester", 40000, 2019, 12, 18);

        try (PrintWriter out = new PrintWriter("E:\\JAVA\\IdeaProjects\\corejava\\src\\corejava.chapter12\\section2\\employee.dat", "UTF-8")) {
            writeData(staff, out);
        }

        try (Scanner in = new Scanner(new FileInputStream("E:\\JAVA\\IdeaProjects\\corejava\\src\\corejava.chapter12\\section2\\employee.dat"), "UTF-8")) {
            Employee[] newStaff = readData(in);

            for (Employee e : newStaff) {
                System.out.println(e);
            }
        }
    }

    private static void writeData(Employee[] employees, PrintWriter out) {
        out.println(employees.length);

        for (Employee e: employees) {
            writeEmployee(out, e);
        }
    }

    private static Employee[] readData(Scanner in) {
        int n = in.nextInt();
        in.nextLine();

        Employee[] employees = new Employee[n];
        for (int i = 0; i < n; i++) {
            employees[i] = readEmployee(in);
        }
        return employees;
    }

    public static void writeEmployee(PrintWriter out, Employee e) {
        out.println(e.getName() + "|" + e.getSalary() + "|" + e.getHireDay());
    }

    public static Employee readEmployee(Scanner in) {
        String line = in.nextLine();
        String[] tokens = line.split("\\|");
        String name = tokens[0];
        double salary = Double.parseDouble(tokens[1]);
        LocalDate hireDate = LocalDate.parse(tokens[2]);
        int year = hireDate.getYear();
        int month = hireDate.getMonthValue();
        int day = hireDate.getDayOfMonth();
        return new Employee(name, salary, year, month, day);
    }
}
