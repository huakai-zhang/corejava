package chapter9.section5;

import javax.swing.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JMenuTest {
    public static void main(String[] args) {

    }

    void fillMenu(JMenu menu, Collection<JMenuItem> items) {
        for (JMenuItem item : items) {
            menu.add(item);
        }
    }

    /*List<JMenuItem> getAllItems(JMenu menu) {
        List<JMenuItem> items = new ArrayList<>();
        for (int i = 0; i < menu.getItemCount(); i++) {
            items.add(menu.getItem(i));
        }
        return items;
    }*/

    List<JMenuItem> getAllItems(final JMenu menu) {
        return new AbstractList<JMenuItem>() {
            @Override
            public JMenuItem get(int index) {
                return menu.getItem(index);
            }

            @Override
            public int size() {
                return menu.getItemCount();
            }
        };
    }
}
