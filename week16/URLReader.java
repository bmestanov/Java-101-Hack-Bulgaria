package week16;

import com.google.gson.Gson;
import week16.models.Forecast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

/**
 * Created on 14.02.17.
 */
public class URLReader {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        String city = scanner.nextLine();
        city = city.replaceAll(" ","_");
        String request = String.format("http://api.openweathermap.org/data/2.5/" +
                "weather?q=%s&appid=9ed81d9300f326bbd3f1ef06bb0f1207&units=metric",city);

        URL url = new URL(request);

        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(url.openStream())
        );

        String response;
        while ((response = reader.readLine()) != null) {
            stringBuilder.append(response);
        }

        //System.out.println(stringBuilder.toString());
        Forecast forecast = new Gson().fromJson(stringBuilder.toString(),Forecast.class);
        System.out.println(forecast);
    }
}
