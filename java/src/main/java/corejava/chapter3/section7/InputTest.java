package corejava.chapter3.section7;

import java.util.Scanner;

/**
 * @author Spring Zhang
 * @date 2019/4/15 9:51
 */
public class InputTest {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("What's your name?");
        String name = in.nextLine();

        System.out.println("How old are you?");
        int age = in.nextInt();

        System.out.println("Hello " + name + ", next year, you'll be " + (age + 1));

        /*Console cons = System.console();
        String username = cons.readLine();
        System.out.println("username=" + username);
        char[] passwd = cons.readPassword("Password:");*/
    }

}
