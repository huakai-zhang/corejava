package juc;

class myNumber {
    volatile int number = 10;

    public void add() {
        this.number = 11;
    }
}

public class Visibility {
    public static void main(String[] args) {
        myNumber myNumber = new myNumber();
        System.out.println(Thread.currentThread().getName() + "\t *********come in");
        new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myNumber.add();
            System.out.println(Thread.currentThread().getName() + "\t A update number,number value: " + myNumber.number);
        }, "AAA").start();

        while (myNumber.number == 10) {

        }

        System.out.println(Thread.currentThread().getName() + "\t mission is over");
    }
}
