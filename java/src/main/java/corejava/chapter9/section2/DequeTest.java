package corejava.chapter9.section2;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

public class DequeTest {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedBlockingDeque<>(1);
        queue.add("11");
        queue.add("22");
        System.out.println(queue.size());
    }
}
