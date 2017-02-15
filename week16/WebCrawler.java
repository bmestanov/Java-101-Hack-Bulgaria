package week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created on 14.02.17.
 */
public class WebCrawler {
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://www.whirlpool.com/laundry-1/laundry-2/washers-3/102110047/");
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(url.openStream())
        );

        String response;
        while ((response = reader.readLine()) != null) {
            stringBuilder.append(response);
        }

        response = stringBuilder.toString();

        Pattern pattern = Pattern.compile("\\b8href=\".*\"\\b");
        Matcher matcher = pattern.matcher(response);

        while (matcher.matches()) {
            System.out.println(matcher.group());
        }
    }
}
