package practice.java.clone;

/**
 * 浅拷贝仅仅复制所考虑的对象，而不复制它所引用的对象
 */
public class Test {
    public static void main(String[] args) throws CloneNotSupportedException {
        Son son = new Son(12, "Tom", new Father(36, "Jone"));
        Son son1 = (Son) son.clone();
        son1.getFather().setName("Mic");
        System.out.println(son.getFather().getName());
        System.out.println(son1.getFather().getName());
    }
}
