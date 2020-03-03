package factory.abstr;

import factory.Car;

/**
 * @author Spring Zhang
 * @date 2020/3/3 17:41
 */
public abstract class AbstractFactory {
    protected abstract Car getCar();

    public Car getCar(String name) {
        if ("BMW".equalsIgnoreCase(name)) {
            return  new BwmFactory().getCar();
        } else if ("Benz".equalsIgnoreCase(name)) {
            return new BenzFactory().getCar();
        } else if ("Audi".equalsIgnoreCase(name)) {
            return new AudiFactory().getCar();
        } else {
            System.out.println("这个产品没有");
            return null;
        }
    }
}
