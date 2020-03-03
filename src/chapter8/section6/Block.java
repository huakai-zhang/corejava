package chapter8.section6;

import java.io.File;
import java.util.Scanner;

/**
 * @author Spring Zhang
 * @date 2019/12/3 11:19
 */
public abstract class Block {

    public abstract void body() throws Exception;

    public Thread toThread() {
        return new Thread() {
            @Override
            public void run() {
                try {
                    body();
                } catch (Throwable t) {
                    Block.<RuntimeException>throwAs(t);
                }
            }
        };
    }

    @SuppressWarnings("unchecked")
    public static <T extends Throwable> void throwAs(Throwable e) throws T {
        throw (T) e;
    }

    public static void main(String[] args) {
        new Block() {
            @Override
            public void body() throws Exception {
                Scanner in = new Scanner(new File("ququx"), "UTF-8");
                while (in.hasNext()) {
                    System.out.println(in.next());
                }
            }
        }.toThread().start();
    }
}
