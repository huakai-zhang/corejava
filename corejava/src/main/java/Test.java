import org.apache.ibatis.io.Resources;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Spring Zhang
 * @date 2019/12/16 13:20
 */
public class Test {

    public static void main(String[] args) throws IOException {
        InputStream inputStream= Resources.getResourceAsStream("mybatis-config.xml");
        System.out.println(inputStream.read());
    }
}
