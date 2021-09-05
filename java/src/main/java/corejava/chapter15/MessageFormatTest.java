package corejava.chapter15;

import java.text.MessageFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

public class MessageFormatTest {
    public static void main(String[] args) {
        String msg = MessageFormat.format("On {2,date,long}, a {0} destroyed {1} houses and caused {3,number,currency} of damage.",
                "hurricane", 99, new GregorianCalendar(1999, 0, 1).getTime(), 10.0E8);
        System.out.println(msg);
        MessageFormat mf = new MessageFormat("On {2,date,long}, a {0} destroyed {1} houses and caused {3,number,currency} of damage.", Locale.US);
        String s = mf.format(new Object[]{"hurricane", 99, new GregorianCalendar(1999, 0, 1).getTime(), 10.0E8});
        System.out.println(s);
    }
}
