package chapter14;

import java.time.*;

/**
 * @author Spring Zhang
 * @date 2020/1/2 16:48
 */
public class ZonedTimes {
    public static void main(String[] args) {
        ZonedDateTime apollo11launch = ZonedDateTime.of(2020, 1, 2, 14, 39, 0, 0, ZoneId.of("Asia/Shanghai"));
        System.out.println("apollo11launch: " + apollo11launch);
        Instant instant = apollo11launch.toInstant();
        System.out.println("instant: " + instant);

        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("UTC"));
        System.out.println("zonedDateTime: " + zonedDateTime);

        // 中欧地区在3月31日2:00切换夏令时，试图构建2:30，实际得到的是3:30
        ZonedDateTime skipped = ZonedDateTime.of(LocalDate.of(2013, 3, 31),
                LocalTime.of(2, 30), ZoneId.of("Europe/Berlin"));
        System.out.println("skipped: " + skipped);

        // 反过来，时钟会回拨慢一个小时，同一个本地时间就会出现两次
        ZonedDateTime ambiguous = ZonedDateTime.of(LocalDate.of(2013, 10, 27),
                LocalTime.of(2, 30), ZoneId.of("Europe/Berlin"));
        ZonedDateTime anHourLater = ambiguous.plusHours(1);
        System.out.println("ambiguous: " + ambiguous);
        System.out.println("anHourLater: " + anHourLater);

        ZonedDateTime meeting = ZonedDateTime.of(LocalDate.of(2013, 10, 31),
                LocalTime.of(14, 30), ZoneId.of("America/Los_Angeles"));
        System.out.println("meeting: " + meeting);
        // 小心！夏令时不起作用
        ZonedDateTime nextMeeting = meeting.plus(Duration.ofDays(7));
        System.out.println("nextMeeting: " + nextMeeting);
        nextMeeting = meeting.plus(Period.ofDays(7));
        System.out.println("nextMeeting: " + nextMeeting);
    }
}
