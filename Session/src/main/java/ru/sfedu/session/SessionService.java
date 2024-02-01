package ru.sfedu.session;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class SessionService {
    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    SessionRepository sessionRepository;

    public List<Session> findByDate(String start_date, String end_date, String client_id) {
        return sessionRepository.findSessionsByDateBetweenAndClientId(start_date, end_date, client_id).orElse(null);
    }

    public void createSession(String client_id, String date) {
        Session session = new Session(client_id, date);
        sessionRepository.save(session);
    }

    public void sendResultToCalendar(List<Object> sessionList) {
        System.out.println("sending result to the calendar");
        WebClient webClient = WebClient.builder().baseUrl("http://localhost:8082").build();
        String url = "/SimplePsyCalendar/V1/calendar/result";
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
