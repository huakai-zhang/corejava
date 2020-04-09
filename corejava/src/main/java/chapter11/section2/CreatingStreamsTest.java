package chapter11.section2;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class CreatingStreamsTest {
    public static void main(String[] args) throws IOException {
        String contents = new String(Files.readAllBytes(Paths.get("E:\\IDEAFile\\corejava\\src\\chapter9\\section2\\alice30.txt")), StandardCharsets.UTF_8);
        Stream<String> words = Stream.of(contents.split("\\PL+"));
        Stream<String> song = Stream.of("gently", "down", "the", "stream");

        /*Stream<String> echos = Stream.generate(() -> "Echo");
        Stream<Double> randoms = Stream.generate(Math::random);
        Stream<BigInteger> integers = Stream.iterate(BigInteger.ZERO, n -> n.add(BigInteger.ONE));
        integers.forEach(System.out::println);*/

        //Stream<String> ws = Pattern.compile("\\PL+").splitAsStream(contents);
        //ws.forEach(System.out::println);


        Stream<String> lines = Files.lines(Paths.get("E:\\IDEAFile\\corejava\\src\\chapter9\\section2\\alice30.txt"));
        lines.forEach(System.out::println);
    }
}
