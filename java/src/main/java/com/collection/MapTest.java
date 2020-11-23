package com.collection;

import java.util.HashMap;
import java.util.Hashtable;

public class MapTest {

    private static class Person{
        int idCard;
        String name;

        public Person(int idCard, String name) {
            this.idCard = idCard;
            this.name = name;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Person person = (Person) obj;
            return this.idCard == person.idCard;
        }
    }

    public static void main(String[] args) {
        HashMap<Person, String> map = new HashMap<>();
        Person person = new Person(1234, "乔峰");
        map.put(person, "天龙八部");
        System.out.println(map.get(new Person(1234, "萧峰")));
    }
}
