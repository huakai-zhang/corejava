package chapter4.section2;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class LocalDateTest {
    public static void main(String[] args) {
        LocalDate aa = LocalDate.now();
        System.out.println(aa);

        LocalDate newYear = LocalDate.of(2019, 10, 23);

        LocalDate aThousandDaysLater = newYear.plusDays(1000);
        System.out.println(aThousandDaysLater.getYear());
        System.out.println(aThousandDaysLater.getMonthValue());
        System.out.println(aThousandDaysLater.getDayOfMonth());
    }
}
