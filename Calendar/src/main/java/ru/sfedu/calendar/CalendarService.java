package ru.sfedu.calendar;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class CalendarService {
    public void sendRequestToSession(String specialistId, String startDate, String endDate) {
        WebClient webClient = WebClient.builder().baseUrl("http://localhost:8083").build();
        String url = "/SimplePsySession/V1/session/search"; // Укажите вашу конечную точку API
        webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(url)
                        .queryParam("client_id", specialistId)
                        .queryParam("start_date", startDate)
                        .queryParam("end_date", endDate)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(responseBody -> {
                    // Обработка ответа, если необходимо
                    System.out.println("Response: " + responseBody);
                });
    }

    public void sendResultToSpecialist(List<Object> sessionList) {
        WebClient webClient = WebClient.builder().baseUrl("http://localhost:8081").build();
        String url = "/SimplePsyCalendar/V1/calendar"; // Укажите вашу конечную точку API
        webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(url)
                        .queryParam("sessionList", sessionList)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(responseBody -> {
                    // Обработка ответа, если необходимо
                    System.out.println("Response: " + responseBody);
                });
        System.out.println(sessionList.get(0));
    }
}
