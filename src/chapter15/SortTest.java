package chapter15;

import java.text.Collator;
import java.text.Normalizer;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class SortTest {
    public static void main(String[] args) {
        String[] s = new String[]{"Zulu", "able", "zebra", "Ångstrom", "America"};
        Arrays.sort(s);
        System.out.println(Arrays.toString(s));

        Collator coll = Collator.getInstance(Locale.getDefault());
        List<String> words = Arrays.asList(s);
        words.sort(coll);
        System.out.println(words);

        String name = "Ångstrom";
        String normalized = Normalizer.normalize(name, Normalizer.Form.NFD);
        System.out.println(normalized);
    }
}
