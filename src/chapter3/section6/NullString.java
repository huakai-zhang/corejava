package chapter3.section6;

public class NullString {
    public static void main(String[] args) {
        String str = "";
        if (str.length() == 0) {
            System.out.println(1);
        }
        if(str.equals("")){
            System.out.println(2);
        }

    }
}
