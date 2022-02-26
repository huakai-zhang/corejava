package corejava.chapter8.section5;

import corejava.chapter8.section2.Pair;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.Dictionary;
import java.util.Hashtable;

/**
 * @author Spring Zhang
 * @date 2019/12/2 16:25
 */
public class DictionaryTest {
    public static void main(String[] args) {
        Dictionary<Integer, Component> labelTable = new Hashtable<>();
        labelTable.put(0, new JLabel(new ImageIcon("nine.gif")));
        labelTable.put(1, new JLabel(new ImageIcon("ten.gif")));
        JSlider jSlider = new JSlider();
        jSlider.setLabelTable(labelTable);
        @SuppressWarnings("unchecked")
        Dictionary<Integer, Component> lt = jSlider.getLabelTable();

       /* Pair<String>[] table = new Pair[10];
        Object[] objarray = table;
        //objarray[0] = "Hello";
        objarray[0] = new Pair<Employee>();*/

        Pair<String>[] table = (Pair<String>[]) new Pair<?>[10];
        //table[0] = new Pair<Employee>();
    }

    @SafeVarargs
    public static <T> void addAll(Collection<T> coll, T... ts) {
        for (T t : ts) {
            coll.add(t);
        }
    }
}
