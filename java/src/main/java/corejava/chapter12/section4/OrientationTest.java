package corejava.chapter12.section4;

import java.io.*;

public class OrientationTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Orientation original = Orientation.HORIZONTAL;
        System.out.println(original == Orientation.HORIZONTAL);
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("E:\\IDEAFile\\corejava\\src\\corejava.chapter12\\section4\\orientation.dat"));
        out.writeObject(original);
        out.close();
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("E:\\IDEAFile\\corejava\\src\\corejava.chapter12\\section4\\orientation.dat"));
        Orientation saved = (Orientation) in.readObject();
        System.out.println(saved == Orientation.HORIZONTAL);
    }
}
