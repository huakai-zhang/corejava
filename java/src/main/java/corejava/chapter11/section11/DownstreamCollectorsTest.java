package corejava.chapter11.section11;

import static java.util.stream.Collectors.*;

import java.util.*;
import java.util.stream.Stream;

/**
 * @author Spring Zhang
 * @date 2019/12/18 10:14
 */
public class DownstreamCollectorsTest {
    public static void main(String[] args) {
        Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
        /*Map<String, Set<Locale>> countryToLocales = locales.collect(
                groupingBy(Locale::getCountry, toSet()));*/
        /*Map<String, Long> countryToLocaleCounts = locales.collect(*/
        /*        groupingBy(Locale::getCountry, counting()));*/
        /*Map<String, Integer> stateToCityPopulation = cities.collect(*/
        /*        groupingBy(City::getState, summarizingInt(City::getPopulation)));*/
        /*Map<String, Optional<City>> stateToLargestCity = cities.collect(*/
        /*        groupingBy(City::getState, maxBy(Comparator.comparing(City::getPopulation))));*/
        /*Map<String, Optional<String>> stateToLongstCityName = cities.collect(*/
        /*        groupingBy(City::getState, mapping(City::getName, maxBy(Comparator.comparing(String::length)))));*/
        Map<String, Set<String>> contryToLanguages = locales.collect(
                groupingBy(Locale::getDisplayCountry,
                        mapping(Locale::getDisplayLanguage, toSet())));
    }
}
