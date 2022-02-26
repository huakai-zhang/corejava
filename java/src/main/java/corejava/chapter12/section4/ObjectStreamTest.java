package corejava.chapter12.section4;

import corejava.chapter4.section3.Employee;
import corejava.chapter5.Manager;

import java.io.*;

public class ObjectStreamTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Employee harry = new Employee("Harry Hacker", 50000, 2019, 12, 23);
        Manager carl = new Manager("Carl Cracker", 80000, 2019, 12, 20);
        carl.setSecretary(harry);
        Manager tony = new Manager("Tony Tester", 40000, 2019, 12, 19);
        tony.setSecretary(harry);

        Employee[] staff = new Employee[3];
        staff[0] = carl;
        staff[1] = harry;
        staff[2] = tony;

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("E:\\IDEAFile\\corejava\\src\\corejava.chapter12\\section4\\employee.dat"))) {
            out.writeObject(staff);
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("E:\\IDEAFile\\corejava\\src\\corejava.chapter12\\section4\\employee.dat"))) {
            Employee[] newStaff = (Employee[]) in.readObject();
            // 秘书对象重新加载之后是唯一的，当秘书被恢复时，会反映到经理们的secretary域中
            newStaff[1].raiseSalary(10);
            for (Employee e : newStaff) {
                System.out.println(e);
            }
        }
    }
}
