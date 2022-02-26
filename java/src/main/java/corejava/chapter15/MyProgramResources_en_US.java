package corejava.chapter15;

import java.awt.*;
import java.util.ListResourceBundle;

public class MyProgramResources_en_US extends ListResourceBundle {
    private static final Object[][] contents = {
            {"backgroundColor", Color.blue},
            {"defaultPaperSize", new double[]{216, 279}}
    };
    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
