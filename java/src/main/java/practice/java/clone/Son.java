package practice.java.clone;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/9/7
 */
public class Son implements Cloneable {
    private int age;
    private String name;
    private Father father;

    public Son() {
    }

    public Son(int age, String name, Father father) {
        this.age = age;
        this.name = name;
        this.father = father;
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

    public Father getFather() {
        return father;
    }

    public void setFather(Father father) {
        this.father = father;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        //return super.clone();
        Son son = (Son) super.clone();
        son.setFather((Father) son.getFather().clone());
        return son;
    }
}
