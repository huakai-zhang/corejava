package com.spring.design.prototype.greatestsage;

import java.util.Date;

/**
 * @author Spring Zhang
 * @date 2020/3/5 15:54
 */
public class Monkey {

    int height;

    int weight;

    protected Date birthday;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
