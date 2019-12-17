package chapter10.section7;

import java.util.*;

/**
 * @author Spring Zhang
 * @date 2019/12/17 12:58
 */
public class OptionalMySelfTest {
    public static void main(String[] args) throws IllegalAccessException {
        Optional<String> optionalString = Optional.empty();
        //String result = optionalString.orElse("");
        String result = optionalString.orElseGet(() -> Locale.getDefault().getDisplayName());
        //String result = optionalString.orElseThrow(IllegalAccessException::new);
        System.out.println(result);
        List<String> results = new ArrayList<>();
        optionalString.ifPresent(results::add);
        Optional<Boolean> added = optionalString.map(results::add);
        System.out.println(added);

    }

    public static Optional<Double> inverse(Double x) {
        return x == 0 ? Optional.empty() : Optional.of(1 / x);
    }

    public static Optional<Double> squareRoot(Double x) {
        return x < 0 ? Optional.empty() : Optional.of(Math.sqrt(x));
    }
}
