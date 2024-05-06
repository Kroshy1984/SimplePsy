package ru.sfedu.simplepsyspecialist.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import ru.sfedu.simplepsyspecialist.dto.CustomerDTO;
import ru.sfedu.simplepsyspecialist.dto.ProblemDTO;
import ru.sfedu.simplepsyspecialist.dto.SessionDTO;
import ru.sfedu.simplepsyspecialist.entity.Specialist;
import ru.sfedu.simplepsyspecialist.entity.SpecialistRole;
import ru.sfedu.simplepsyspecialist.exception.NotFoundException;
import ru.sfedu.simplepsyspecialist.exception.SpecialistNotFoundException;
import ru.sfedu.simplepsyspecialist.repo.SpecialistRepository;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SpecialistService {

    SpecialistRepository specialistRepository;
    BCryptPasswordEncoder passwordEncoder;

    public SpecialistService(SpecialistRepository specialistRepository) {
        this.specialistRepository = specialistRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Specialist save(Specialist specialist) {
        return specialistRepository.save(specialist);
    }

    public Specialist registerNewSpecialist(Specialist specialist) {
        System.out.println(specialist.getPassword());
        String password = passwordEncoder.encode(specialist.getPassword());
        specialist.setPassword(password);
        specialist.setSpecialistRole(SpecialistRole.USER_ROLE);

        return specialistRepository.save(specialist);
    }

    public Specialist authorizeSpecialist(Specialist s)
    {

        Optional<Specialist> optionalUser = specialistRepository.findByUsername(s.getUsername());

        Specialist specialist = optionalUser.orElseThrow(() -> new SpecialistNotFoundException("User with username " + s.getUsername() + " not found"));

        if(passwordEncoder.matches(s.getPassword(), specialist.getPassword()))
        {
            return specialist;
        }
        else
        {
            throw new NotFoundException("Wrong password");
        }
    }

    public Specialist findById(String id) {
        return specialistRepository.findById(id).orElse(null);
    }

    public Specialist findByUsername(String username)
    {
        System.out.println("Finding...");
        return specialistRepository.findByUsername(username).orElseThrow(() -> new SpecialistNotFoundException("User with username " + username + " not found"));
    }

    public void sendRequestToSession(String specialistId, String startDate, String endDate) {
        String baseUrl = System.getenv().getOrDefault("SESSION_SERVICE_URL", "http://localhost:8083");
        String url = "/SimplePsySession/V1/session/search";
        WebClient webClient = WebClient.builder().baseUrl(baseUrl).build();

        Object result =  webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(url)
                        .queryParam("specialist_id", specialistId)
                        .queryParam("start_date", startDate)
                        .queryParam("end_date", endDate)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();
        System.out.println("got the result: " + result.toString());
    }
    public void createNewSession(String clientEmail, String specialistId, String problem, LocalDateTime date)
    {
        String clientId = findClientByEmail(clientEmail);
        System.out.println("Client was found");
        if(clientId.isEmpty())
        {
            throw new NotFoundException("Client with email " + clientEmail + "not found\n can not create new session");
        }
        String baseUrl = System.getenv().getOrDefault("SESSION_SERVICE_URL", "http://localhost:8083");
        String url = "/SimplePsySession/V1/session/new";
        WebClient webClient = WebClient.builder().baseUrl(baseUrl).build();

        Object result =  webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path(url)
                        .queryParam("clientId", clientId)
                        .queryParam("specialistId", specialistId)
                        .queryParam("problem", problem)
                        .queryParam("date", date)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();

        System.out.println("got the result: " + result.toString());
    }
    public String findClientByEmail(String clientEmail) {
        String baseUrl = System.getenv().getOrDefault("CLIENT_SERVICE_URL", "http://localhost:8086");
        String url = "/SimplePsyClient/V1/client/findByEmail";
        WebClient webClient = WebClient.builder().baseUrl(baseUrl).build();

        String result = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(url)
                        .queryParam("clientEmail", clientEmail)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .flatMap(body -> {
                    System.out.println("Успешный ответ");
                    return Mono.just(body);
                })
                .onErrorResume(WebClientResponseException.class, ex -> {
                    // Обработка ответа с кодом статуса 404
                    if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                        System.out.println("Ресурс не найден");
                        return Mono.empty();
                    } else {
                        return Mono.error(ex);
                    }
                })
                .block();
        System.out.println("got the clientId: " + result.toString());
        return result;
//        if (response.statusCode().equals(HttpStatus.OK)) {
//            String result = response.bodyToMono(String.class).block();
//            System.out.println("Успешный ответ: " + result);
//            return result;
//        }
//        else {
//            throw new NotFoundException("Client with email " + clientEmail + "not found");
//        }

    }

    public List<CustomerDTO> getAllCustomers() {
        System.out.println("In method getAllCustomers");
        String baseUrl = System.getenv().getOrDefault("CUSTOMER_SERVICE_URL", "http://localhost:8080");
        String url = "/SimplePsy/V1/customer/getAllCustomers";
        WebClient webClient = WebClient.builder().baseUrl(baseUrl).build();

        Mono<ResponseEntity<List<CustomerDTO>>> response = webClient.get()
                .uri(url)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<List<CustomerDTO>>() {});

        List<CustomerDTO> customers = response.block().getBody();

        System.out.println("List of customers names:");
        for (int i = 0; i < customers.size(); i++) {
            System.out.println(customers.get(i).getName());
        }
        return customers;
    }

    public CustomerDTO findCustomerById(String customerId) {
        String baseUrl = System.getenv().getOrDefault("CUSTOMER_SERVICE_URL", "http://localhost:8080");
        String url = "/SimplePsy/V1/customer/getCustomerById";
        WebClient webClient = WebClient.builder().baseUrl(baseUrl).build();

        Mono<ResponseEntity<CustomerDTO>> response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(url)
                        .queryParam("customerId", customerId)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<CustomerDTO>() {});

        CustomerDTO customerDTO = response.block().getBody();
        System.out.println("Got the customer with name: " + customerDTO.getName());
        return customerDTO;
    }

    public String findCustomerByContactData(String data) {
        String baseUrl = System.getenv().getOrDefault("CUSTOMER_SERVICE_URL", "http://localhost:8080");
        String url = "/SimplePsy/V1/customer/findCustomerByContactData";
        WebClient webClient = WebClient.builder().baseUrl(baseUrl).build();

        Mono<ResponseEntity<String>> response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(url)
                        .queryParam("data", data)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<>() {});

        String result = response.block().getBody();
        System.out.println("Got the customer with data: " + data);
        return result;
    }

    public void deleteCustomerById(String customerId, String specialistId) {
        Specialist specialist = specialistRepository.findById(specialistId).get();
        specialist.deleteCustomerById(customerId);
        specialistRepository.save(specialist);
        String baseUrl = System.getenv().getOrDefault("CUSTOMER_SERVICE_URL", "http://localhost:8080");
        String url = "/SimplePsy/V1/customer/deleteCustomerById";
        WebClient webClient = WebClient.builder().baseUrl(baseUrl).build();

        ResponseEntity<String> response = webClient.delete()
                .uri(uriBuilder -> uriBuilder
                        .path(url)
                        .queryParam("customerId", customerId)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(String.class).block();

        String result = response.getBody();
        System.out.println("The result of deleting customer with id: "
                + customerId + " - " + result);
    }

    public String saveProblem(String problem) {
        String baseUrl = System.getenv().getOrDefault("PROBLEM_SERVICE_URL", "http://localhost:8087");
        String url = "/SimplePsyProblem/V1/problem/new";
        WebClient webClient = WebClient.builder().baseUrl(baseUrl).build();

        ResponseEntity<String> response = webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path(url)
                        .queryParam("problem", problem)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(String.class).block();
        System.out.println("The result of creating a new problem:\n" + response.getBody());
        return response.getBody();
    }

    public String saveCustomer(CustomerDTO customer, String problem) {
        String baseUrl = System.getenv().getOrDefault("CUSTOMER_SERVICE_URL", "http://localhost:8080");
        String url = "/SimplePsy/V1/customer/new";
        WebClient webClient = WebClient.builder().baseUrl(baseUrl).build();

        String problemId = saveProblem(problem);
        customer.addProblem(problemId);
        System.out.println("Specialist CustomerDTO: " + customer.getProblemsId().get(0));
        ResponseEntity<String> response = webClient.post()
                .uri(url)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(customer))
                .retrieve()
                .toEntity(String.class).block();

        System.out.println("Got the id of a new saved customer: " + response.getBody());
        sendScoringNotification(response.getBody(), problemId);
        return response.getBody();
    }

    public List<SessionDTO> getAllSessions(String specialistId) {
        String baseUrl = System.getenv().getOrDefault("SESSION_SERVICE_URL", "http://localhost:8083");
        String url = "/SimplePsySession/V1/session/calendar";
        WebClient webClient = WebClient.builder().baseUrl(baseUrl).build();

        ResponseEntity<List<SessionDTO>> result =  webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(url)
                        .queryParam("specialistId", specialistId)
                        .build())
                .retrieve()
                        .toEntityList(SessionDTO.class)
                                .block();
        System.out.println("got the result: " + result.toString());
        for (int i = 0; i < result.getBody().size(); i++) {
            System.out.println(result.getBody().get(i).getDate().toString());
            System.out.println(result.getBody().get(i).getClientDTO().getName());
            System.out.println(result.getBody().get(i).getClientDTO().getSurname());
            System.out.println(result.getBody().get(i).getClientDTO().getDateOfBirth());
        }
        return result.getBody();
    }
    public  List<List<SessionDTO>> groupSessionsByDay(List<SessionDTO> sessions) {
        System.out.println("into groupSessionsByDay method");
        List<List<SessionDTO>> sessionsByDayOfWeek = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            sessionsByDayOfWeek.add(new ArrayList<>());
        }

        // Проходимся по каждой сессии и добавляем ее в соответствующий список
        for (SessionDTO session : sessions) {
            DayOfWeek dayOfWeek = session.getDate().getDayOfWeek();
            int dayIndex = dayOfWeek.getValue() % 7; // Индекс дня недели (0 - Понедельник, ..., 6 - Воскресенье)
            sessionsByDayOfWeek.get(dayIndex).add(session);
        }
        System.out.println("sessionsByDayOfWeek.size(): " + sessionsByDayOfWeek.size());
        for (int i = 0; i < sessionsByDayOfWeek.size(); i++) {
            List<SessionDTO> sessionDTOS = sessionsByDayOfWeek.get(i);
            System.out.println("sessionDTOS.size(): " + sessionDTOS.size());
            for (int j = 0; j < sessionDTOS.size(); j++) {
                System.out.println("Date of session: " + sessionDTOS.get(j).getDate());
                System.out.println("Client name " + sessionDTOS.get(j).getClientDTO().getName());
            }
        }
        return sessionsByDayOfWeek;
    }

    public Specialist findSpecialist(String customerId) {
        return specialistRepository.findByCustomerIdsIn(customerId).get();
    }

    public void sendEmailtoSpecialist(String email, String specialistName, String customerName, String problemId) {
        System.out.println("sending email method");
        String baseUrl = System.getenv().getOrDefault("NOTIFICATIONS_SERVICE_URL", "http://localhost:8085");
        String url = "/emails/scoring-result";
        WebClient webClient = WebClient.builder().baseUrl(baseUrl).build();
        Mono<ResponseEntity<String>> result = webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path(url)
                        .queryParam("email", email)
                        .queryParam("specialistName", specialistName)
                        .queryParam("customerName", customerName)
                        .queryParam("problemId", problemId)
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

    public void updateCustomer(CustomerDTO customerDTO) {
        String baseUrl = System.getenv().getOrDefault("CUSTOMER_SERVICE_URL", "http://localhost:8080");
        String url = "/SimplePsy/V1/customer/update";
        WebClient webClient = WebClient.builder().baseUrl(baseUrl).build();

        ResponseEntity<String> response = webClient.post()
                .uri(url)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(customerDTO))
                .retrieve()
                .toEntity(String.class).block();
        System.out.println("Result of updating the customer: " + response.getBody());
    }
    public void sendScoringNotification(String customerId, String problemId)
    {
        String baseUrl = System.getenv().getOrDefault("NOTIFICATIONS_SERVICE_URL", "http://localhost:8085");
        String url = "/emails/scoring-invitation";
        System.out.println("Finding customer with id " + customerId);
        CustomerDTO customerDTO = findCustomerById(customerId);
        List<String> customerParams = List.of(problemId, customerDTO.getName(), customerDTO.getContact().getEmail());
        System.out.println("Got the customer with name: " + customerParams.get(1) + "\nAnd email: " + customerParams.get(2));
        System.out.println("The id of problem is: " + customerParams.get(0));
        WebClient webClient = WebClient.builder().baseUrl(baseUrl).build();

        ResponseEntity<String> response = webClient.post()
                .uri(url)
                .body(BodyInserters.fromValue(customerParams))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(String.class).block();
        System.out.println("result of sending scoring invitation: " + response.getBody());
    }
    //передавать в кастомера id проблемы и кастомера чтобы добавить ему(кастомеру) новую проблему в список
    public void addCustomerProblem(String customerId, String problem) {
        String problemId = saveProblem(problem);
        String baseUrl = System.getenv().getOrDefault("CUSTOMER_SERVICE_URL", "http://localhost:8080");
        String url = "/SimplePsy/V1/customer/problem/new";
        WebClient webClient = WebClient.builder().baseUrl(baseUrl).build();

        ResponseEntity<String> response = webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path(url)
                        .queryParam("customerId", customerId)
                        .queryParam("problemId", problemId)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(String.class).block();

        System.out.println("Result of adding new problem to the customer: " + response.getBody());
        sendScoringNotification(customerId, problemId);
    }

    // Запрос идет в Customer и из Customer в Problem
    public List<ProblemDTO> getAllCustomersProblems(String customerId)
    {
        String baseUrl = System.getenv().getOrDefault("CUSTOMER_SERVICE_URL", "http://localhost:8080");
        String url = "/SimplePsy/V1/customer/problems";
        WebClient webClient = WebClient.builder().baseUrl(baseUrl).build();

        ResponseEntity<List<ProblemDTO>> response = webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path(url)
                        .queryParam("customerId", customerId)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntityList(ProblemDTO.class).block();
        System.out.println("In method getAllCustomersProblems the result of the first one: " +
                response.getBody().get(0).getDescriptionOfProblem() +
                response.getBody().get(0).getId());
        return response.getBody();
    }

    public CustomerDTO findCustomerByProblemId(String problemId) {
        String baseUrl = System.getenv().getOrDefault("CUSTOMER_SERVICE_URL", "http://localhost:8080");
        String url = "/SimplePsy/V1/customer/getCustomerByProblemId";
        WebClient webClient = WebClient.builder().baseUrl(baseUrl).build();

        ResponseEntity<CustomerDTO> response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(url)
                        .queryParam("problemId", problemId)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(CustomerDTO.class).block();

        CustomerDTO customerDTO = response.getBody();
        System.out.println("Got the customer with name: " + customerDTO.getName());
        return customerDTO;
    }

    public List<String> getScoringAnswersByProblemId(String problemId) {
        String baseUrl = System.getenv().getOrDefault("PROBLEM_SERVICE_URL", "http://localhost:8087");
        String url = "/SimplePsyProblem/V1/problem/getScoringAnswersByProblemId";
        WebClient webClient = WebClient.builder().baseUrl(baseUrl).build();

        Mono<ResponseEntity<List<String>>> response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(url)
                        .queryParam("problemId", problemId)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<>() {});
        List<String> answers = response.block().getBody();
        System.out.println("Got the result in method getScoringAnswersByProblemId: ");
        for (int i = 0; i < answers.size(); i++) {
            System.out.println(answers.get(i));
            System.out.println(i);
        }
        return answers;
    }
}
