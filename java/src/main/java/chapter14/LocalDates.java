package chapter14;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

/**
 * @author Spring Zhang
 * @date 2020/1/2 13:10
 */
public class LocalDates {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        System.out.println("today: " + today);

        LocalDate alonzosBirthday = LocalDate.of(1903, 4, 14);
        alonzosBirthday = LocalDate.of(1903, Month.JUNE, 14);
        System.out.println("alonzosBirthday: " + alonzosBirthday);

        LocalDate programmersDay = LocalDate.of(2018, 1, 1).plusDays(255);
        System.out.println("programmersDay: " + programmersDay);

        LocalDate nationalDay = LocalDate.of(2019, Month.NOVEMBER, 1);
        LocalDate midAutumnFestival = LocalDate.of(2019, Month.SEPTEMBER, 13);

        System.out.println("Until nationalDay: " + midAutumnFestival.until(nationalDay));
        System.out.println("Until nationalDay: " + midAutumnFestival.until(nationalDay, ChronoUnit.DAYS));

        System.out.println(LocalDate.of(2020, 1, 31).plusMonths(1));
        System.out.println(LocalDate.of(2020, 3, 31).minusMonths(1));

        DayOfWeek startOfLastMillennium = LocalDate.of(1900, 1, 1).getDayOfWeek();
        System.out.println("startOfLastMillennium: " + startOfLastMillennium);
        System.out.println(startOfLastMillennium.getValue());
        System.out.println(DayOfWeek.SATURDAY.plus(3));
    }
}
