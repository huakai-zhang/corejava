package corejava.chapter15;

import java.awt.*;
import java.util.ListResourceBundle;

public class MyProgramResources_de extends ListResourceBundle {
    private static final Object[][] contents = {
            {"backgroundColor", Color.black},
            {"defaultPaperSize", new double[]{210, 297}}
    };
    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
