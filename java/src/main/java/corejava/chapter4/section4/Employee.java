package corejava.chapter4.section4;

public class Employee {
    private static int nextId = 1;
    private int id;

    public void setId() {
        this.id = nextId;
        nextId++;
    }

    public int getId() {
        return id;
    }

    public static int getNextId() {
        return nextId;
    }

    public static void main(String[] args) {
        Employee e1 = new Employee();
        e1.setId();

        Employee e2 = new Employee();
        e2.setId();
        System.out.println(e1.getId());
        System.out.println(e2.getId());
    }
}
