package ru.sfedu.scoring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    public Scoring saveScoring(Scoring scoring)
    {
        return scoringRepository.save(scoring);
    }
    public void createNewClient(List<String> client) {
        System.out.println("Client params: ");
        client.stream().forEach(System.out::println);
        System.out.println(client.get(4));
        String baseUrl = System.getenv().getOrDefault("CLIENT_SERVICE_URL", "http://localhost:8086");
        String url = "/SimplePsyClient/V1/client/new";
        WebClient webClient = WebClient.builder().baseUrl("http://localhost:8086").build();

        Mono<ResponseEntity<String>> result = webClient.post()
                .uri(url)
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
        String baseUrl = System.getenv().getOrDefault("NOTIFICATIONS_SERVICE_URL", "http://localhost:8085");
        String url = "/emails/text";
        WebClient webClient = WebClient.builder().baseUrl(baseUrl).build();

        Mono<ResponseEntity<String>> result = webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path(url)
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

    public void sendCustomerId(String customerId) {
        System.out.println("Sending customerId to Specialist: " + customerId);
        String baseUrl = System.getenv().getOrDefault("SPECIALIST_SERVICE_URL", "http://localhost:8081");
        String url = "/SimplePsySpecialist/V1/specialist/find-customer";
        WebClient webClient = WebClient.builder().baseUrl(baseUrl).build();

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
    public void sendProblemId(String problemId) {
        System.out.println("Sending problemId to Specialist: " + problemId);
        String baseUrl = System.getenv().getOrDefault("SPECIALIST_SERVICE_URL", "http://localhost:8081");
        String url = "/SimplePsySpecialist/V1/specialist/find-customer/byProblemId";
        WebClient webClient = WebClient.builder().baseUrl(baseUrl).build();

        ResponseEntity<String> result = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(url)
                        .queryParam("problemId", problemId)
                        .build())
                .retrieve()
                .toEntity(String.class)
                .block();
        System.out.println("result of sending customer id to find-customer: " + result.getBody());
    }
    public void saveCustomersScoring(String problemId, String scoringId) {
        String baseUrl = System.getenv().getOrDefault("PROBLEM_SERVICE_URL", "http://localhost:8087");
        String url = "/SimplePsyProblem/V1/problem/saveCustomersScoring";
        WebClient webClient = WebClient.builder().baseUrl(baseUrl).build();

        ResponseEntity<String> response = webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path(url)
                        .queryParam("problemId", problemId)
                        .queryParam("scoringId", scoringId)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(String.class).block();

        System.out.println("The result of saving scoring: " + response.getBody());
    }
}
