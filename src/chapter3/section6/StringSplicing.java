package chapter3.section6;

public class StringSplicing {
    public static void main(String[] args) {
        String expletive = "Expletive";
        String PG13 = "deleted";
        String message = expletive + PG13;
        System.out.println(message);

        int age =13;
        String rating = "PG" + age;
        System.out.println(rating);

        System.out.println("The answer is " + age);

        String all = String.join(" /", "S", "M", "L", "XL");
        System.out.println(all);
        //S /M /L /XL

    }
}
