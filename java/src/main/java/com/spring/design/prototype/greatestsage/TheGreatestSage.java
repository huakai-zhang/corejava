package com.spring.design.prototype.greatestsage;

import java.io.*;
import java.util.Date;

/**
 * @author Spring Zhang
 * @date 2020/3/5 15:54
 */
public class TheGreatestSage extends Monkey implements Cloneable, Serializable {

    // 金箍棒
    private GoldRingedStaff staff;

    public TheGreatestSage() {
        this.staff = new GoldRingedStaff();
        this.birthday = new Date();
        this.height = 150;
        this.weight = 30;
        System.out.println("------------------------------");
    }

    @Override
    public Object clone() {
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        try {
            //return super.clone();
            // 序列化
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(this);

            // 反序列化
            bis = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bis);
            TheGreatestSage copy = (TheGreatestSage) ois.readObject();
            copy.birthday = new Date();
            return copy;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                bos.close();
                oos.close();
                ois.close();
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void change() {
        TheGreatestSage copySage = (TheGreatestSage) clone();
        System.out.println("大圣本尊生日是：" + this.birthday.getTime());
        System.out.println("克隆大圣的生日是：" + copySage.birthday.getTime());
        System.out.println("大圣本尊和克隆大圣是否为同一个对象：" + (this == copySage));
        System.out.println("大圣本尊持有的金箍棒和克隆大圣持有的金箍棒是否为同一个对象：" + (this.getStaff() == copySage.getStaff()));

    }

    public GoldRingedStaff getStaff() {
        return staff;
    }

    public void setStaff(GoldRingedStaff staff) {
        this.staff = staff;
    }
}
