package chapter15;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.ResourceBundle;

public class WenbenTest {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        /*PrintWriter out = new PrintWriter("", "Windows-1252");
        Charset platformEncoding = Charset.defaultCharset();*/
        System.out.printf("Hello%nWorld%n");
        // Hello
        // World
        System.out.println("100€");
        ResourceBundle bundle = ResourceBundle.getBundle("MyProgramStrings", Locale.getDefault());
        String conputeButtonLabel = bundle.getString("computeButton");
        Color backgroudColor = (Color) bundle.getObject("backgroundColor");
        double[] paperSize = (double[]) bundle.getObject("defaultPaperSize");

    }
}
