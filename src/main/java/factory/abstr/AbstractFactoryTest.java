package factory.abstr;

/**
 * @author Spring Zhang
 * @date 2020/3/3 17:47
 */
public class AbstractFactoryTest {
    public static void main(String[] args) {
        DefaultFactory defaultFactory = new DefaultFactory();

        System.out.println(defaultFactory.getCar("benz"));
    }
}
