package corejava.chapter3.section5;

public class NumericalConversion {
    public static void main (String[] array){
        int i1=12;
        int i2=34;
        double d1=(i1+i2)*1.2;
        float f1=(float)(i1+i2);
        System.out.println(d1+","+f1);
        byte b1=67;
        byte b2=89;
        //系统将转换成int型运算，需要强制转换符
        byte b3=(byte)(b1+b2);
        System.out.println(b3);
        double d2=1e200;
        //会产生溢出
        float f2 = (float)d2;
        System.out.println(f2);
        //必须加f
        float f3=1.23f;
        long l1=123;
        //必须加l
        long l2=300000000L;
        float f = l1+l2+f3;
        //强制转换会舍去小数部分(不是四舍五入)
        long l = (long)f;
        System.out.println(l);
        // 55.199999999999996,46.0
        // -100
        // Infinity
        // 300000128
    }
}
