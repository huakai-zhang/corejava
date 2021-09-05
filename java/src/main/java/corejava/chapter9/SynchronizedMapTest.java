package corejava.chapter9;

import corejava.chapter4.section3.Employee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Spring Zhang
 * @date 2019/12/11 9:58
 */
public class SynchronizedMapTest {
    public static void main(String[] args) {
        Map<String, Employee> map = Collections.synchronizedMap(new HashMap<>());
        ArrayList<String> strings = new ArrayList<>();
    }
}
