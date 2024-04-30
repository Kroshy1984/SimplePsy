package ru.sfedu.notifications.telegram.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.sfedu.notifications.telegram.model.Spec;
import ru.sfedu.notifications.telegram.model.SpecRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SpecService {

    SpecRepository specRepository;

    @Autowired
    public SpecService(SpecRepository specRepository) {
        this.specRepository = specRepository;
    }

    public List<LocalDateTime> sendRequestToGetSessions(String email)
    {
        Spec spec = specRepository.findByUsername(email);
        System.out.println(spec.getEmail());
        String specialist_id = spec.getId();
        System.out.println(specialist_id);
        String baseUrl = System.getenv().getOrDefault("SESSION_SERVICE_URL", "http://localhost:8083");
        String url = "/SimplePsySession/V1/session/searchAll";
        WebClient webClient = WebClient.builder().baseUrl(baseUrl).build();

        ResponseEntity<List<LocalDateTime>> response = webClient.get()
                .uri(uriBuilder -> uriBuilder.path(url)
                        .queryParam("specialist_id", specialist_id)
                        .build())
                .retrieve()
                .toEntityList(LocalDateTime.class)
                .block();
        System.out.println("got the response: " + response);
        List<LocalDateTime> sessions = response.getBody();
        System.out.println("got the session: " + sessions);
        return sessions;
    }
}
