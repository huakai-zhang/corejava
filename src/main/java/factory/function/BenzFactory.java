package factory.function;

import factory.Benz;
import factory.Car;

/**
 * @author Spring Zhang
 * @date 2020/3/3 17:24
 */
public class BenzFactory implements Factory {
    @Override
    public Car getCar() {
        return new Benz();
    }
}
