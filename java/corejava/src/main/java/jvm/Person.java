package jvm;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/9/4
 */
public class Person {

    private String personName;

    public String getPersonName() {
        return personName;
    }

    public Person(String personName) {
        this.personName = personName;
    }

    public Person() {
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }
}
