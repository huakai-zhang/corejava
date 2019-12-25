package chapter12.section4;

import java.io.ObjectStreamException;
import java.io.Serializable;

public class Orientation implements Serializable {
    public static final Orientation HORIZONTAL = new Orientation(1);
    public static final Orientation VERTICAL = new Orientation(2);
    private int value;

    private Orientation(int x) {
        value = x;
    }

    protected  Object readResolve() throws Exception {
        if (value == 1) {
            return Orientation.HORIZONTAL;
        }
        if (value == 2) {
            return Orientation.VERTICAL;
        }
        throw new Exception();
    }
}
