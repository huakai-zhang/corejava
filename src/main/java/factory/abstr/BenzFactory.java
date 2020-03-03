package factory.abstr;

import factory.Benz;
import factory.Car;
import factory.function.Factory;

/**
 * @author Spring Zhang
 * @date 2020/3/3 17:24
 */
public class BenzFactory extends AbstractFactory {
    @Override
    public Car getCar() {
        return new Benz();
    }
}
