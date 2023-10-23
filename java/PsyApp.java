import rest.WeatherApi;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PsyApp {

    public static void main(String[] args) {
        String baseUrl = "http://api.weatherapi.com/v1";
        String apiKey = "3e00ce72746340598f371039230203";

        WeatherApi api = new WeatherApi(baseUrl, apiKey);

        String apiResource = "current";
        String fmt = "json";

        Map<String, String> params = new HashMap<>();
        params.put("q", "London");
        params.put("aqi", "no");
        params.put("days", "1");

        try {
            api.sendRequest(apiResource, fmt, params);
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }

    }

}
