package corejava.chapter12.section2;

import java.nio.charset.Charset;

/**
 * @author Spring Zhang
 * @date 2019/12/20 16:42
 */
public class CharsetTest {
    public static void main(String[] args) {
        System.out.println(Charset.defaultCharset());
        System.out.println(Charset.availableCharsets());
    }
}
