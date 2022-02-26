package com.serializable;

import java.io.*;

/**
 * @author 春阳
 * @date 2020-12-18 18:26
 */
public class SerializeDemo {
    public static void main(String[] args) {
        // 序列化操作
        serializePerson();

        SerializablePerson.height = 5;
        // 反序列化操作
        SerializablePerson person = deSerializePerson();
        System.out.println(person);
        System.out.println("静态变量：" + person.height);
        System.out.println("未实现Serialize的父类变量：" + person.getSuperAge());
    }

    private static  void serializePerson() {
        try {
            ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(new File("person")));
            SerializablePerson person = new SerializablePerson();
            person.setAge(18);
            person.setName("Spring");
            person.setSuperAge(36);
            oo.writeObject(person);
            oo.flush();
            oo.writeObject(person);
            oo.flush();
            // 写一次大小为 83，写两次为88，不是叠加
            System.out.println("序列化成功:" + new File("person").length());

            oo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static SerializablePerson deSerializePerson() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("person")));
            SerializablePerson person1 = (SerializablePerson) ois.readObject();
            SerializablePerson person2 = (SerializablePerson) ois.readObject();
            // 存储的对象指向同一个引用
            System.out.println(person1 == person2);
            return person1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
