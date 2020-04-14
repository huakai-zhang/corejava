package chapter9.section2;

import java.time.LocalDate;
import java.util.PriorityQueue;

public class PriorityQueueTest {
    public static void main(String[] args) {
        PriorityQueue<LocalDate> pq = new PriorityQueue<>();
        pq.add(LocalDate.of(2019, 03, 13));
        pq.add(LocalDate.of(2019, 02, 17));
        pq.add(LocalDate.of(2019, 06, 24));
        pq.add(LocalDate.of(2019, 04, 30));

        for (LocalDate date: pq) {
            System.out.println(date);
        }
        System.out.println("...");
        while (!pq.isEmpty()) {
            System.out.println(pq.remove());
        }
        // 2019-02-17
        // 2019-03-13
        // 2019-06-24
        // 2019-04-30
        // ...
        // 2019-02-17
        // 2019-03-13
        // 2019-04-30
        // 2019-06-24
    }
}
