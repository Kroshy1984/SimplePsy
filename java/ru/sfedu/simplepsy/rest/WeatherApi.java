package ru.sfedu.simplepsy.rest;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class WeatherApi {

    /**
     * Базовый URL запроса
     */
    private final String base;

    /**
     * API ключ для доступа к функцианалу сервиса
     */
    private final String apiKey;

    public WeatherApi(String base, String apiKey) {
        this.base = base;
        this.apiKey = apiKey;
    }

    private String constructQuery(Map<String, String> params) {
        StringBuilder query = new StringBuilder();
        query.append("key=");
        query.append(apiKey);
        for (Map.Entry<String, String> entry : params.entrySet()) {
            query.append("&");
            query.append(entry.getKey());
            query.append("=");
            query.append(entry.getValue());
        }
        return query.toString();
    }

    private static String constructUrl(String base, String resource, String fmt, String query) {
        return base + "/" + resource + "." + fmt + "?" + query;
    }

    /**
     * Подробную информацию о возможных запросах
     * можно найти на <a href="https://www.weatherapi.com/docs">сайте документации</a>
     *
     * @param resource интересуемый нас функционал сервиса
     * @param fmt формат ответа (JSON/XML)
     * @param params параметры запроса (q, day, aqi, ...)
     */
    public void sendRequest(String resource, String fmt, Map<String, String> params)
            throws IOException, InterruptedException {
        String query = constructQuery(params);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(constructUrl(base, resource, fmt, query)))
                .build();

        HttpResponse<String> response;

        try (HttpClient client = HttpClient.newHttpClient()) {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        }

        System.out.println(response.body().toString());
    }

}