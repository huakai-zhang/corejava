package corejava.chapter12.section1;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Spring Zhang
 * @date 2019/12/19 13:52
 */
public class OutputStream {
    public static void main(String args[]){
        try{
            byte[] bytes = {122,104,97,110,103};
            FileOutputStream out = new FileOutputStream("E:/Java/IdeaProjects/corejava/src/corejava.chapter12/section1/2.txt");
            out.write(bytes);
            out.close();
        } catch (IOException e1){
            e1.printStackTrace();
        }
    }
}
