package ru.sfedu.simplepsyspecialist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.sfedu.simplepsyspecialist.entity.Scoring;
import ru.sfedu.simplepsyspecialist.repo.ScoringRepository;

import java.util.List;

@Service
public class ScoringService {
    ScoringRepository scoringRepository;
    ClientService clientService;
    CustomerService customerService;
    ProblemService problemService;
    @Autowired
    public ScoringService(ScoringRepository scoringRepository, ClientService clientService, CustomerService customerService, ProblemService problemService) {
        this.scoringRepository = scoringRepository;
        this.clientService = clientService;
        this.customerService = customerService;
        this.problemService = problemService;
    }

    public Scoring save(Scoring scoring) {
//        Scoring scoring = scoringRepository.findById(scoringId).get();
//
//        System.out.println("Found the scoring with id: " + scoring.getId());
////        scoring.setAnswers(answers);
////        System.out.println("Ответы: " + scoring.getAnswers());
//        Scoring resultScoring = scoringRepository.save(scoring);
//        List<Scoring> scoringsWithEmptyAnswers = scoringRepository.findScoringsWithEmptyAnswers();
//        scoringRepository.deleteAll(scoringsWithEmptyAnswers);
        Scoring scor = scoringRepository.save(scoring);
        Scoring savedScoring = scoringRepository.findById(scor.getId()).get();
        System.out.println(savedScoring.getId());
        System.out.println(savedScoring.getQuestions().get(0).getQuestionText());
        System.out.println(savedScoring.getQuestions().get(1).getQuestionText());
        System.out.println(savedScoring.getQuestions().get(0).getOptions().get(0));
        System.out.println(savedScoring.getQuestions().get(1).getOptions().get(0));

        return savedScoring;
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
        problemService.saveCustomersScoring(problemId, scoringId);
    }

    public Scoring findById(String id) {
        return scoringRepository.findById(id).get();
    }

//    public List<String> getScoringAnswers(String scoringId) {
//        if (scoringRepository.findById(scoringId).isPresent()) {
//            return scoringRepository.findById(scoringId).get().getAnswers();
//        } else {
//            return null;
//        }
//    }
}
