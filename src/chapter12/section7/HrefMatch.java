package chapter12.section7;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Spring Zhang
 * @date 2019/12/27 17:31
 */
public class HrefMatch {
    public static void main(String[] args) {
        try {
            String urlString = "http://horstmann.com";
            InputStreamReader in = new InputStreamReader(new URL(urlString).openStream(), StandardCharsets.UTF_8);

            StringBuilder input = new StringBuilder();
            int ch;
            while ((ch = in.read()) != -1) {
                input.append((char) ch);
            }

            String patternString = "<a\\s+href\\s*=\\s*(\"[^\"]*\"|[^\\s>]*)\\s*>";
            Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(input);

            while (matcher.find()) {
                String match = matcher.group();
                System.out.println(match);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
