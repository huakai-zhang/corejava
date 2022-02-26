package corejava.chapter3.section4;

public class UnicodeTest {
    public static void main(String[] args) {
        // 并不是一个由引号(U+0022)包围加号构成的字符串。实际上\u0022会在解析之前转换为"，这会得到""+""，也就是一个空串
        System.out.println("\u0022+\u0022");
        // 这会产生一个语法错误，因为\\u后面未跟着4个十六进制数
        // Look inside c:\\users'
    }
}
