package corejava.chapter8.section2;

import java.time.LocalDate;

/**
 * @author Spring Zhang
 * @date 2019/12/2 16:11
 */
public class DateInterval extends Pair<LocalDate> {
    @Override
    public LocalDate getSecond() {
        return (LocalDate) super.getSecond();
    }
}
