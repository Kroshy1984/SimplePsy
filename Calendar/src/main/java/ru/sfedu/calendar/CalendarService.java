package ru.sfedu.calendar;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class CalendarService {
    public void sendRequestToSession(String specialistId, String startDate, String endDate) {
        System.out.println("sending request to the session");
        System.out.println(specialistId);
        System.out.println(startDate);
        System.out.println(endDate);
        WebClient webClient = WebClient.builder().baseUrl("http://localhost:8083").build();
        String url = "/SimplePsySession/V1/session/search";
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
                    System.out.println("Response: " + responseBody);
                });
    }

    public void sendResultToSpecialist(List<Object> sessionList) {
        System.out.println("sending result to the specialist");
        System.out.println(sessionList.get(0));
        System.out.println(sessionList.get(1));
        WebClient webClient = WebClient.builder().baseUrl("http://localhost:8081").build();
        String url = "/SimplePsySpecialist/V1/specialist/result";
        webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(url)
                        .queryParam("sessionList", sessionList)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(responseBody -> {
                    System.out.println("Response: " + responseBody);
                });
    }
}
