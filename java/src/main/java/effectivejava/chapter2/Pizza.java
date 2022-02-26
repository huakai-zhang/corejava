package effectivejava.chapter2;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author zhangchunyang
 * @since 2021/9/12 9:56
 * Builder 模式也适用于类层次结构
 */
public class Pizza {
    // 配料
    public enum Topping { HAN, MUSHROOM, ONION, PEPPER, SAUSAGE }
    final Set<Topping> toppings;

    abstract static class Builder<T extends Builder<T>> {
        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);

        public T addToppings(Topping topping) {
            toppings.add(Objects.requireNonNull(topping));
            return self();
        }

        abstract Pizza build();

        protected abstract  T self();
    }

    Pizza(Builder<?> builder) {
        toppings = builder.toppings.clone();
    }

    public static void main(String[] args) {
        MyPizza pizza = new MyPizza.Builder(MyPizza.Size.SMALL).addToppings(Topping.SAUSAGE).addToppings(Topping.ONION).build();
        Calzone calzone = new Calzone.Builder().addToppings(Topping.HAN).sauceInside().build();
    }
}
