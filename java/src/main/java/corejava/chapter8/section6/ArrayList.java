package corejava.chapter8.section6;

/**
 * @author Spring Zhang
 * @date 2019/12/3 10:21
 */
public class ArrayList<E> {
    private Object[] emements;

    @SuppressWarnings("unchecked")
    public E get(int n) {
        return (E) emements[n];
    }

    public void set(int n, E e) {
        emements[n] = e;
    }
}
