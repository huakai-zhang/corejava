package corejava.chapter4.section4;

import java.text.NumberFormat;

public class NumberFormatTest {
    public static void main(String[] args) {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
        NumberFormat percentFormatter = NumberFormat.getPercentInstance();
        double x = 0.1;
        System.out.println(currencyFormatter.format(x));
        System.out.println(percentFormatter.format(x));
        // ￥0.10
        // 10%
    }
}
