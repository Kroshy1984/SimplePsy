package ru.sfedu.scoring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ScoringService {
    ScoringRepository scoringRepository;

    @Autowired
    public ScoringService(ScoringRepository scoringRepository) {
        this.scoringRepository = scoringRepository;
    }

    public Scoring save(List<String> answers) {
        Scoring scoring = new Scoring();
        /*List<String> clientsParams = new ArrayList<>();
        clientsParams.addAll(answers.subList(0, 6));
        createNewClient(clientsParams);*/
        scoring.setAnswers(answers);
        System.out.println("Ответы: " + scoring.getAnswers());
        return scoringRepository.save(scoring);
    }

    public void createNewClient(List<String> client) {
        System.out.println("Client params: ");
        client.stream().forEach(System.out::println);
        System.out.println(client.get(4));
        WebClient webClient = WebClient.builder().baseUrl("http://localhost:8086/SimplePsyClient/V1/client/new").build();

        Mono<ResponseEntity<String>> result = webClient.post()
                .bodyValue(client)
                .retrieve()
                .toEntity(String.class)
                .flatMap(response -> {
                    if (response.getStatusCode() == HttpStatus.OK) {
                        System.out.println("Sending email");
                        sendEmailNotificationCreatedAccount(client.get(0), client.get(4));
                        System.out.println("Запрос выполнен успешно");
                        return Mono.just(response);
                    } else {
                        // Обработка неуспешного запроса
                        System.out.println("didn't handle the request, error's code: " + response.getStatusCode());
                        return Mono.empty();
                    }
                });

        result.subscribe(); // Запуск запроса
        System.out.println("got the result: " + result.toString());
    }

    public void sendEmailNotificationCreatedAccount(String name, String email)
    {
        System.out.println("sending email method");
        WebClient webClient = WebClient.builder().baseUrl("http://localhost:8085/emails/text").build();
        Mono<ResponseEntity<String>> result = webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("email", email)
                        .queryParam("name", name)
                        .build())
                .retrieve()
                .toEntity(String.class)
                .flatMap(response -> {
                    if (response.getStatusCode() == HttpStatus.OK) {
                        System.out.println("mail's sent");
                        return Mono.just(response);
                    } else {
                        // Обработка неуспешного запроса
                        System.out.println("didn't sent the email, error's code: " + response.getStatusCode());
                        return Mono.empty();
                    }
                });

        result.subscribe(); // Запуск запроса
        System.out.println("got the result: " + result.toString());
    }

    public void getScoringResult() {
    }

    // TODO: Доделать передачу customerId в микросервис Specialist
    public void sendCustomerId(String customerId) {
        System.out.println("Sending customerId to Specialist: " + customerId);
        String url = "/SimplePsySpecialist/V1/specialist/find-customer";
        WebClient webClient = WebClient.builder().baseUrl("http://localhost:8081").build();

        ResponseEntity<String> result = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(url)
                        .queryParam("customerId", customerId)
                        .build())
                .retrieve()
                .toEntity(String.class)
                .block();
        System.out.println("result of sending customer id to find-customer: " + result.getBody());
    }

}
