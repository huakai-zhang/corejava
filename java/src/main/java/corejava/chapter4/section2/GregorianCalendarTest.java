package corejava.chapter4.section2;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class GregorianCalendarTest {
    public static void main(String[] args) {
        GregorianCalendar someDay = new GregorianCalendar(2019, 10, 23);
        someDay.add(Calendar.DAY_OF_MONTH, 1000);
        System.out.println(someDay.get(Calendar.YEAR));
        System.out.println(someDay.get(Calendar.MONTH) + 1);
        System.out.println(someDay.get(Calendar.DAY_OF_MONTH));
    }
}
