package corejava.chapter8.section7;

import corejava.chapter5.Manager;
import corejava.chapter8.section2.Pair;

/**
 * @author Spring Zhang
 * @date 2019/12/2 16:08
 */
public class Test {
    public static void main(String[] args) {

    }

    public static void minmaxBonus(Manager[] a, Pair<? super Manager> result) {
        if (a.length == 0) {
            return;
        }
        Manager min = a[0];
        Manager max = a[0];
        for (int i = 1; i < a.length; i++)
        {
            if (min.getBonus() > a[i].getBonus()) {
                min = a[i];
            }
            if (max.getBonus() < a[i].getBonus()) {
                max = a[i];
            }
        }
        result.setFirst(min);
        result.setSecond(max);
    }
}
