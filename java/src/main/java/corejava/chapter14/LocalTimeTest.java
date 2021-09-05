package corejava.chapter14;

import java.time.LocalTime;

/**
 * @author Spring Zhang
 * @date 2020/1/2 14:09
 */
public class LocalTimeTest {
    public static void main(String[] args) {
        LocalTime rightNow = LocalTime.now();
        LocalTime bedtime = LocalTime.of(22, 30);
        // plus和minus按照一天24小时操作
        LocalTime wakeup = bedtime.plusHours(8);
        System.out.println(rightNow);
        System.out.println(bedtime);
        System.out.println(wakeup);
    }
}
