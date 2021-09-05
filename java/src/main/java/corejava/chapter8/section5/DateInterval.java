package corejava.chapter8.section5;

import corejava.chapter8.section2.Pair;

import java.time.LocalDate;

public class DateInterval extends Pair<LocalDate> {
    @Override
    public void setSecond(LocalDate second) {
        if (second.compareTo(getFirst()) >= 0) {
            super.setSecond(second);
        }
    }

    public static void main(String[] args) {
        /*DateInterval interval = new DateInterval(...);
        Pair<LocalDate> pair = interval;
        pair.setSecond(aDate);*/
    }
}
