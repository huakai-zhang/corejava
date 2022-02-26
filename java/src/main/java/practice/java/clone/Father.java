package practice.java.clone;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/9/7
 */
public class Father implements Cloneable {
    private int age;
    private String name;

    public Father() {
    }

    public Father(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
