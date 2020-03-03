package factory.abstr;

import factory.Car;

/**
 * @author Spring Zhang
 * @date 2020/3/3 17:48
 */
public class DefaultFactory extends AbstractFactory {

    private AudiFactory factory = new AudiFactory();

    @Override
    protected Car getCar() {
        return factory.getCar();
    }
}
