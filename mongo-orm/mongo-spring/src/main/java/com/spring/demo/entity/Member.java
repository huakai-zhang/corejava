package com.spring.demo.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/7/7
 */
@Document(collection = "Member")
public class Member implements Serializable {

    private String name;

    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "MemberDao{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
