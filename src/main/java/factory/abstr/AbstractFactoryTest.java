package factory.abstr;

/**
 * @author Spring Zhang
 * @date 2020/3/3 17:47
 */
public class AbstractFactoryTest {
    // 工厂模式特点
    // 隐藏复杂的逻辑处理过程，只关心执行结果

    public static void main(String[] args) {
        DefaultFactory defaultFactory = new DefaultFactory();

        System.out.println(defaultFactory.getCar("benz"));
    }
}
