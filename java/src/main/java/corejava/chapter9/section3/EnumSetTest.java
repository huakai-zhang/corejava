package corejava.chapter9.section3;

import corejava.chapter4.section3.Employee;

import java.util.EnumMap;
import java.util.EnumSet;

public class EnumSetTest {
    public static void main(String[] args) {
        EnumSet<Weekday> always = EnumSet.allOf(Weekday.class);
        EnumSet<Weekday> never = EnumSet.noneOf(Weekday.class);
        EnumSet<Weekday> workday = EnumSet.range(Weekday.MONDAY, Weekday.FRIDAY);
        EnumSet<Weekday> mwf = EnumSet.of(Weekday.MONDAY, Weekday.WEDNESDAY, Weekday.FRIDAY);

        EnumMap<Weekday, Employee> personInCharge = new EnumMap<>(Weekday.class);
    }
}
enum Weekday {
    MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY,SUNDAY
}