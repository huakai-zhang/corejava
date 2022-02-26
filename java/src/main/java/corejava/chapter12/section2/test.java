package corejava.chapter12.section2;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        String fileName="E:\\IDEAFile\\corejava\\src\\corejava.chapter12\\section2\\f.txt";
        PrintWriter toFile=null;

        try{
            toFile=new PrintWriter(fileName);         //将数据流outStream连接到名为f.txt的文件
            //此方法将文件连接到数据库时，总是从一个空文件开始
            //若文件已存在，则原来的文件内容丢失；若不存在，则重新创建一个空文件
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            System.out.println("PrintWriter error opening the file:"+fileName);
            System.exit(0);
        }
        System.out.println("Please input four lines of text:");         //本例中控制输入4行
        Scanner keyboard=new Scanner(System.in);         //使用Scanner获得输入

        for(int count=1;count<=4;count++){
            String line=keyboard.nextLine();         //获取一行内容
            toFile.println(count+". "+line);         //PrintWriter的println写文件方法与System.out.println写屏幕方法类似
        }

        System.out.println("Four lines were written to "+fileName);
        toFile.close();      //显示关闭数据流，避免数据丢失
    }
}
