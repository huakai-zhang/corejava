package chapter12.section3;

import chapter4.section3.Employee;

import java.io.*;
import java.time.LocalDate;

/**
 * @author Spring Zhang
 * @date 2019/12/20 17:49
 */
public class RandomAccessTest {

    public static void main(String[] args) throws IOException {
        Employee[] staff = new Employee[3];
        staff[0] = new Employee("Carl Cracker", 75000, 2019, 12, 20);
        staff[1] = new Employee("Harry Hacker", 50000, 2019, 12, 19);
        staff[2] = new Employee("Tony Tester", 40000, 2019, 12, 18);

        try (DataOutputStream out = new DataOutputStream(new FileOutputStream("E:\\JAVA\\IdeaProjects\\corejava\\src\\chapter12\\JDBCTest\\employee.dat"))) {
            for (Employee e : staff) {
                writeData(out, e);
            }
        }

        try (RandomAccessFile in = new RandomAccessFile("E:\\JAVA\\IdeaProjects\\corejava\\src\\chapter12\\JDBCTest\\employee.dat", "r")) {
            int n = (int)(in.length() / Employee.RECODE_SIZE);
            Employee[] newStaff = new Employee[n];

            for (int i = n - 1; i >= 0; i--) {
                newStaff[i] = new Employee();
                in.seek(i * Employee.RECODE_SIZE);
                newStaff[i] = readData(in);
            }

            for (Employee e : newStaff) {
                System.out.println(e);
            }
        }
    }

    // 为了写出一条固定尺寸的记录，直接以二进制方式写出所有字段
    public static void writeData(DataOutput out, Employee e) throws IOException {
        DataIO.writeFixedString(e.getName(), Employee.NAME_SIZE, out);
        out.writeDouble(e.getSalary());

        LocalDate hireDay = e.getHireDay();
        out.writeInt(hireDay.getYear());
        out.writeInt(hireDay.getMonthValue());
        out.writeInt(hireDay.getDayOfMonth());
    }

    public static Employee readData(DataInput in) throws IOException {
        String name = DataIO.readFixedString(Employee.NAME_SIZE, in);
        double salary = in.readDouble();
        int y = in.readInt();
        int m = in.readInt();
        int d = in.readInt();
        return new Employee(name, salary, y, m - 1, d);
    }
}
