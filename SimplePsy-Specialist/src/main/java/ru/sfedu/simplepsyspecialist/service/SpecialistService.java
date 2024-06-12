package ru.sfedu.simplepsyspecialist.service;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.sfedu.simplepsyspecialist.entity.*;
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

    ClientService clientService;
    CustomerService customerService;
    SpecialistRepository specialistRepository;
    BCryptPasswordEncoder passwordEncoder;

    public SpecialistService(SpecialistRepository specialistRepository, ClientService clientService, CustomerService customerService) {
        this.specialistRepository = specialistRepository;
        this.clientService = clientService;
        this.customerService = customerService;
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
        Client client = clientService.findByEmail(clientEmail);
        return client.getId();
    }

    public List<Customer> getAllCustomers() {
       return new ArrayList<>();
    }

    public Customer findCustomerById(String customerId) {
        return new Customer();
    }

    public String findCustomerByContactData(String data) {
        return null;
    }

    public void deleteCustomerById(String customerId, String specialistId) {
        System.out.println("In method deleteCustomerById");
        customerService.deleteCustomer(customerId);
        Specialist specialist = specialistRepository.findById(specialistId).get();
        specialist.deleteCustomerById(customerId);
        specialistRepository.save(specialist);
    }

    public String saveProblem(String problem) {
       return null;
    }

    public String saveCustomer(Customer customer, String problem) {
        return null;
    }

    public List<Session> getAllSessions(String specialistId) {

        return new ArrayList<>();
    }
    public  List<List<Session>> groupSessionsByDay(List<Session> sessions) {
        System.out.println("into groupSessionsByDay method");
        List<List<Session>> sessionsByDayOfWeek = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            sessionsByDayOfWeek.add(new ArrayList<>());
        }

        // Проходимся по каждой сессии и добавляем ее в соответствующий список
        for (Session session : sessions) {
            DayOfWeek dayOfWeek = session.getDate().getDayOfWeek();
            System.out.println(session.getDate() + ": " + dayOfWeek + ": " + dayOfWeek.getValue());
            int dayIndex = dayOfWeek.getValue() - 1; // Индекс дня недели (0 - Понедельник, ..., 6 - Воскресенье)
            sessionsByDayOfWeek.get(dayIndex).add(session);
        }
        System.out.println("sessionsByDayOfWeek.size(): " + sessionsByDayOfWeek.size());
        for (int i = 0; i < sessionsByDayOfWeek.size(); i++) {
            List<Session> sessionDTOS = sessionsByDayOfWeek.get(i);
            System.out.println("sessionDTOS.size(): " + sessionDTOS.size());
            for (int j = 0; j < sessionDTOS.size(); j++) {
                System.out.println("Date of session: " + sessionDTOS.get(j).getDate());
                System.out.println("Client name " + sessionDTOS.get(j).getClient().getName());
            }
        }
        return sessionsByDayOfWeek;
    }

    public Specialist findSpecialist(String customerId) {
        return specialistRepository.findByCustomerIdsIn(customerId).get();
    }

    public void sendEmailtoSpecialist(String email, String specialistName, String customerName, String problemId) {
        System.out.println("sending email method");

        System.out.println("got the result: ");
    }

    public void updateCustomer(Customer customerDTO) {

        System.out.println("Result of updating the customer: " );
    }
    public void sendScoringNotification(String customerId, String problemId)
    {
        String baseUrl = System.getenv().getOrDefault("NOTIFICATIONS_SERVICE_URL", "http://localhost:8085");
        String url = "/emails/scoring-invitation";
        System.out.println("Finding customer with id " + customerId);
        Customer customer = findCustomerById(customerId);
        List<String> customerParams = List.of(problemId, customer.getName(), customer.getContact().getEmail());
        System.out.println("Got the customer with name: " + customerParams.get(1) + "\nAnd email: " + customerParams.get(2));
        System.out.println("The id of problem is: " + customerParams.get(0));

        System.out.println("result of sending scoring invitation: ");
    }
    //передавать в кастомера id проблемы и кастомера чтобы добавить ему(кастомеру) новую проблему в список
    public void addCustomerProblem(String customerId, String problem) {
        String problemId = saveProblem(problem);
        sendScoringNotification(customerId, problemId);
    }

    // Запрос идет в Customer и из Customer в Problem
    public List<Problem> getAllCustomersProblems(String customerId)
    {
        return new ArrayList<>();
    }

    public Customer findCustomerByProblemId(String problemId) {

        System.out.println("Got the customer with name: ");
        return new Customer();
    }

    public List<String> getScoringAnswersByProblemId(String problemId) {
        System.out.println("Got the result in method getScoringAnswersByProblemId: ");
        return new ArrayList<>();
    }

    public void approveScoring(String problemId) {

        System.out.println("Result of approving scoring: " );
    }

    public void cancelScoring(String problemId) {
        System.out.printf("scoring with problemId %s was cancelled", problemId);

        System.out.println("Result of cancelling scoring: ");
    }

    public List<Customer> getAllCustomersWithStatusCustomer() {
        System.out.println("In method getAllCustomersWithStatusCustomer");
        List<Customer> customers = new ArrayList<>();

        System.out.println("List of customers names:");
        for (int i = 0; i < customers.size(); i++) {
            System.out.println(customers.get(i).getName());
        }
        return customers;
    }

    public void changePassword(String email) {
        Specialist specialist = specialistRepository.findByUsername(email).get();
        String baseUrl = System.getenv().getOrDefault("NOTIFICATIONS_SERVICE_URL", "http://localhost:8085");
        String url = "/emails/changePass";

        WebClient webClient = WebClient.builder().baseUrl(baseUrl).build();
        ResponseEntity<String> response = webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path(url)
                        .queryParam("email", email)
                        .queryParam("specialistId", specialist.getId())
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(String.class).block();

        System.out.println("The result in method changePassword: " + response.getBody());
    }

    public void setNewPassword(String specialistId, String newPassword) {
        Specialist specialist = specialistRepository.findById(specialistId).get();
        String password = passwordEncoder.encode(newPassword);
        specialist.setPassword(password);
        specialistRepository.save(specialist);
    }
}
