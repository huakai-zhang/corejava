package chapter14;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

/**
 * @author Spring Zhang
 * @date 2020/1/2 13:51
 */
public class TemporalAdjustersTest {
    public static void main(String[] args) {
        LocalDate firstTuesday = LocalDate.of(2020, 1, 1).with(
                TemporalAdjusters.nextOrSame(DayOfWeek.TUESDAY));
        System.out.println(firstTuesday);

        /*TemporalAdjuster NEXT_WORKDAY = w -> {
            // lambda的参数类型为Temporal，必须强制转型
            LocalDate result = (LocalDate) w;
            do {
                result = result.plusDays(1);
            } while (result.getDayOfWeek().getValue() >= 6);
            return result;
        };
        LocalDate today = LocalDate.now();
        LocalDate backToWork = today.with(NEXT_WORKDAY);
        System.out.println(backToWork);*/

        TemporalAdjuster NEXT_WORKDAY = TemporalAdjusters.ofDateAdjuster(w -> {
            LocalDate result = w;
            do {
                result = result.plusDays(1);
            } while (result.getDayOfWeek().getValue() >= 6);
            return result;
        });
        LocalDate today = LocalDate.now();
        LocalDate backToWork = today.with(NEXT_WORKDAY);
        System.out.println(backToWork);
    }
}
