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
import java.util.Objects;
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
        WebClient webClient = WebClient.builder().baseUrl("http://localhost:8083").build();
        String url = "/SimplePsySession/V1/session/search";
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
        WebClient webClient = WebClient.builder().baseUrl("http://localhost:8083").build();
        String url = "/SimplePsySession/V1/session/new";
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
        WebClient webClient = WebClient.builder().baseUrl("http://localhost:8086").build();
        System.out.println("WebClient");
        String url = "/SimplePsyClient/V1/client/findByEmail";
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

        WebClient webClient = WebClient.builder().baseUrl("http://localhost:8080").build();
        String url = "/SimplePsy/V1/customer/getAllCustomers";
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
        WebClient webClient = WebClient.builder().baseUrl("http://localhost:8080").build();
        String url = "/SimplePsy/V1/customer/getCustomerById";
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

    public boolean findCustomerByContactData(String data) {
        WebClient webClient = WebClient.builder().baseUrl("http://localhost:8080").build();
        String url = "/SimplePsy/V1/customer/findCustomerByContactData";
        Mono<ResponseEntity<Boolean>> response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(url)
                        .queryParam("data", data)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<>() {});

        boolean result = Boolean.TRUE.equals(Objects.requireNonNull(response.block()).getBody());
        System.out.println("Got the customer with data: " + data);
        return result;
    }

    public void deleteCustomerById(String customerId) {
        WebClient webClient = WebClient.builder().baseUrl("http://localhost:8080").build();
        String url = "/SimplePsy/V1/customer/deleteCustomerById";
        Mono<ResponseEntity<String>> response = webClient.delete()
                .uri(uriBuilder -> uriBuilder
                        .path(url)
                        .queryParam("customerId", customerId)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(String.class);

        String result = response.block().getBody();
        System.out.println("The result of deleting customer with id: "
                + customerId + " - " + result);
    }

    public String saveProblem(String problem) {
        WebClient webClient = WebClient.builder().baseUrl("http://localhost:8087").build();
        String url = "/SimplePsyProblem/V1/problem/new";
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
        WebClient webClient = WebClient.builder().baseUrl("http://localhost:8080").build();
        String url = "/SimplePsy/V1/customer/new";
        String problemId = saveProblem(problem);
        customer.setProblemId(problemId);
        System.out.println("Specialist CustomerDTO: " + customer.getProblemId());
        ResponseEntity<String> response = webClient.post()
                .uri(url)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(customer))
                .retrieve()
                .toEntity(String.class).block();

        System.out.println("Got the id of a new saved customer: " + response.getBody());
        return response.getBody();
    }

    public List<SessionDTO> getAllSessions(String specialistId) {
        WebClient webClient = WebClient.builder().baseUrl("http://localhost:8083").build();
        String url = "/SimplePsySession/V1/session/calendar";
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

    public void sendEmailtoSpecialist(String email, String specialistName, String customerName) {
        System.out.println("sending email method");
        WebClient webClient = WebClient.builder().baseUrl("http://localhost:8085/emails/scoring-result").build();
        Mono<ResponseEntity<String>> result = webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("email", email)
                        .queryParam("specialistName", specialistName)
                        .queryParam("customerName", customerName)
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
        WebClient webClient = WebClient.builder().baseUrl("http://localhost:8080").build();
        String url = "/SimplePsy/V1/customer/update";
        ResponseEntity<String> response = webClient.post()
                .uri(url)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(customerDTO))
                .retrieve()
                .toEntity(String.class).block();
        System.out.println("Result of updating the customer: " + response.getBody());
    }
}
