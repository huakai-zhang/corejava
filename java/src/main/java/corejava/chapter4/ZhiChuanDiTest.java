package corejava.chapter4;

import java.util.Arrays;

/**
 * <strong style="color:red">傻吊</strong>
 * @author Spring Zhang
 * @date 2019/4/17 10:30
 */
public class ZhiChuanDiTest {
    public static void main(String[] args) {
        int i = 0;
        String s = "AB";
        StringBuffer sb = new StringBuffer("AB");
        char[] ch={'a','b'};
        TT tt = new TT();
        tt.setI(0);
        change(i, s, tt, sb, ch);
        System.out.println(i + " " + s + " " + tt.getI() + " " + sb.toString() + " " + Arrays.toString(ch));


        TT t2 = new TT();
        t2.setI(111);
        TT.swap(tt, t2);
        System.out.println("t1:" + tt.getI());
        System.out.println("t2:" + t2.getI());
    }

    static void change(int x, String y, TT t, StringBuffer s, char[] c) {
        x = x + 1;
        y = y + "C";
        t.setI(2);
        s.append("C");
        c[0] = 'z';
    }
}
class TT {
    private int i;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public static void swap(TT x , TT y){
        TT temp = x;
        x = y;
        y = temp;
    }
}
