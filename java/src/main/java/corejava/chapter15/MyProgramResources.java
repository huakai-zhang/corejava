package corejava.chapter15;

import java.util.Enumeration;
import java.util.ResourceBundle;

public class MyProgramResources extends ResourceBundle {
    @Override
    protected Object handleGetObject(String key) {
        return null;
    }

    @Override
    public Enumeration<String> getKeys() {
        return null;
    }
}
