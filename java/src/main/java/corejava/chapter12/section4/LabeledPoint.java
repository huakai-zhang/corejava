package corejava.chapter12.section4;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class LabeledPoint implements Serializable {
    private String label;
    private transient Point2D.Double point;

    // 调用defaultWriteObject方法写出描述符和String域label，这是ObjectOutputStream的一个特殊方法，只能在可序列化的writeObject方法中被调用
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeDouble(point.getX());
        out.writeDouble(point.getY());
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        double x = in.readDouble();
        double y = in.readDouble();
        point = new Point2D.Double(x, y);
    }
}
