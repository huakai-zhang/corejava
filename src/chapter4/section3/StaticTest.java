package chapter4.section3;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StaticTest {
    public static void main(String[] args) {
        List<Em> ems = new ArrayList<>();
        for (int i = 0;i < 100;i++) {
            Em em = new Em();
            em.setI();
            ems.add(em);
        }

        for (int i = 0;i < 100;i++) {
            System.out.println(ems.get(i).getI());
        }
    }
}
class Em {
    private int i;
    private static int nextId = 1;

    public void setI() {
        this.i = nextId;
        nextId++;
    }

    public int getI() {
        return this.i;
    }
}
