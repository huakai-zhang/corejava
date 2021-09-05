package corejava.chapter5;

public abstract class Person {
    private String name;

    public Person() {}

    public Person(String name) {
        this.name = name;
    }

    public abstract String getDescription();

    public String getName() {
        return name;
    }
}
