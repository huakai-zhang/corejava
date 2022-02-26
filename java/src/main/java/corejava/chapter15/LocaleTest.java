package corejava.chapter15;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * @author Spring Zhang
 * @date 2020/1/3 13:32
 */
public class LocaleTest {
    public static void main(String[] args) {
        Locale usEnglish = Locale.forLanguageTag("en-US");
        System.out.println(Locale.US.toLanguageTag());
        for (Locale locale: Locale.getAvailableLocales()) {
            System.out.println(locale);
        }
        NumberFormat.getAvailableLocales();

        Locale loc = new Locale("de", "CH");
        System.out.println(loc.getDisplayName(Locale.GERMAN));
        // Deutsch (Schweiz)
    }
}
