package factory.simple;

import factory.Car;

/**
 * @author Spring Zhang
 * @date 2020/3/3 17:35
 */
public class SimpleFactoryTest {
    public static void main(String[] args) {
        // 这边就是消费者
        Car car = new SimpleFactory().getCar("BMW");
        System.out.println(car.getName());
    }
}
