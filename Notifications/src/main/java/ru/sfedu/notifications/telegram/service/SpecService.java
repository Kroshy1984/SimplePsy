package ru.sfedu.notifications.telegram.service;

import com.google.gson.JsonArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.sfedu.notifications.telegram.model.SpecRepository;

@Service
public class SpecService {

    SpecRepository specRepository;

    @Autowired
    public SpecService(SpecRepository specRepository) {
        this.specRepository = specRepository;
    }

    public JsonArray sendRequestToGetSessions(String email)
    {
//        Spec spec = specRepository.findByEmail(email);
//        String specialist_id = spec.getEmail();
        WebClient webClient = WebClient.builder().baseUrl("http://localhost:8083").build();
        String url = "/SimplePsySession/V1/session/searchAll";
        JsonArray result =  webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(url)
                        .queryParam("specialist_id", "65af431d9b7b25354b377d6a")
                        .build())
                .retrieve()
                .bodyToMono(JsonArray.class)
                .block();
        System.out.println("got the result: " + result);
        return result;
    }
}
