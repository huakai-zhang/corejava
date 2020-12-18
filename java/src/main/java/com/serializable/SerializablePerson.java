package com.serializable;

import java.io.IOException;
import java.io.Serializable;

/**
 * @author 春阳
 * @date 2020-12-18 18:15
 */
public class SerializablePerson extends SuperPerson implements Serializable {
    //private static final long serialVersionUID = -3173897838325750141L;

    public static int height = 2;

    private String name;

    private transient int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /*private void writeObject(java.io.ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeObject(age);
    }

    private void readObject(java.io.ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        age=(Integer) s.readObject();
    }*/

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
