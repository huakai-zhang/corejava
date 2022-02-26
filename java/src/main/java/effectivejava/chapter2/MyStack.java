package effectivejava.chapter2;

import java.util.EmptyStackException;

/**
 * @author zhangchunyang
 * @since 2021/9/12 11:21
 * 消除过期的对象引用
 */
public class MyStack {
    private Object[] elements;
    private int size = 0;

    public Object pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        // 如果一个栈先增加再收缩，从栈中弹出的对象不会被当做垃圾回收，因为栈内部维护着对这些对象的过期引用(obsolete reference)
        // return elements[--size];

        Object result = elements[--size];
        elements[--size] = null;
        return result;
    }
}
