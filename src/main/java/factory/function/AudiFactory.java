package factory.function;

import factory.Audi;
import factory.Car;

/**
 * @author Spring Zhang
 * @date 2020/3/3 17:24
 */
public class AudiFactory implements Factory {
    @Override
    public Car getCar() {
        return new Audi();
    }
}
