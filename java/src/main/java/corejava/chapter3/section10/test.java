package corejava.chapter3.section10;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

public class test extends IOException {
    public static void main(String[] args) {
        LocalDate l = LocalDate.of(2016,6,25);
        l = l.plusDays(1000);
        Date date = new Date();
        System.out.println(l.toString());
        System.out.println(date.toString());
    }
}
