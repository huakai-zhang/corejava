import org.apache.ibatis.io.Resources;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Spring Zhang
 * @date 2019/12/16 13:20
 */
public class Test {
    public final static String SDF_TIME = "yyyy-MM-dd HH:mm:ss";
    public static void main(String[] args) throws IOException {
        Date beginDate = formatDate("2020-05-25 16:35:21");
        Date endDate = formatDate("2020-05-25 16:38:22");
        /*InputStream inputStream= Resources.getResourceAsStream("mybatis-config.xml");
        System.out.println(inputStream.read());*/

        SimpleDateFormat format = new SimpleDateFormat(SDF_TIME);
        String beginDateStr = null;
        String endDateStr = null;

        try {
            beginDateStr = format.format(beginDate);
            endDateStr = format.format(endDate);
            beginDate = format.parse(beginDateStr);
            endDate = format.parse(endDateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println((endDate.getTime() - beginDate.getTime()) / (60 * 1000));

    }

    public static Date formatDate(String date) {
        DateFormat fmt = new SimpleDateFormat(SDF_TIME);
        try {
            return fmt.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
