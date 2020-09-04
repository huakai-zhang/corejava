package jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/9/4
 */
public class TestTransferValue {

    public void changeValue1(int age) {
        age = 30;
    }

    public void changeValue2(Person person) {
        person.setPersonName("haha");
    }

    public void changeValue3(String str) {
        str = "xxx";
    }

    public void changeValue4(List<Integer> list) {
        list.add(4);
    }

    public static void main(String[] args) {
        TestTransferValue test = new TestTransferValue();
        int age =20;
        test.changeValue1(age);
        System.out.println("age-----------" + age);

        Person person = new Person("abc");
        test.changeValue2(person);
        System.out.println("personName-----------" + person.getPersonName());

        String str = "abc";
        test.changeValue3(str);
        System.out.println("String-----------" + str);

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        test.changeValue4(list);
        System.out.println(list);
    }

}
