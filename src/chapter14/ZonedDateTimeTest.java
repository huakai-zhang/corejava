package chapter14;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author Spring Zhang
 * @date 2020/1/2 14:33
 */
public class ZonedDateTimeTest {
    public static void main(String[] args) {
        System.out.println(ZoneId.getAvailableZoneIds());
        ZonedDateTime zonedDateTime = ZonedDateTime.of(2020, 1, 2, 14, 39, 0, 0, ZoneId.of("Asia/Shanghai"));
        System.out.println(zonedDateTime);
        // 获得Instant对象
        System.out.println(zonedDateTime.toInstant());
    }
}
