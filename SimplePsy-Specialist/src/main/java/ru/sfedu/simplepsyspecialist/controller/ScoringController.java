package ru.sfedu.simplepsyspecialist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sfedu.simplepsyspecialist.entity.*;
import ru.sfedu.simplepsyspecialist.entity.nested.Gender;
import ru.sfedu.simplepsyspecialist.entity.nested.Sex;
import ru.sfedu.simplepsyspecialist.entity.nested.TypeOfScoring;
import ru.sfedu.simplepsyspecialist.service.ClientService;
import ru.sfedu.simplepsyspecialist.service.CustomerService;
import ru.sfedu.simplepsyspecialist.service.ScoringService;
import ru.sfedu.simplepsyspecialist.service.SpecialistService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
@RequestMapping("/SimplePsy/V1/scoring")
public class ScoringController {

    public List<String> answers = new ArrayList<>();
    ScoringService scoringService;
    ClientService clientService;
    CustomerService customerService;
    SpecialistService specialistService;

    @Autowired
    public ScoringController(ScoringService scoringService,
                             CustomerService customerService,
                             ClientService clientService,
                             SpecialistService specialistService) {
        this.scoringService = scoringService;
        this.clientService = clientService;
        this.customerService = customerService;
        this.specialistService = specialistService;
    }



    @GetMapping("/done")
    public String done()
    {
        return "new-front/test/done";
    }

    @GetMapping("/result/{id}")
    public ResponseEntity<List<String>> getScoringResult(@PathVariable String id)
    {
        scoringService.getScoringResult();
        return null;
    }

    @GetMapping("/creation")
    public String createQuestionnaireForm(@AuthenticationPrincipal UserDetails userDetails,
                                          Model model)
    {
        Specialist specialist = specialistService.findByUsername(userDetails.getUsername());
        model.addAttribute("specialist", specialist);
        return "new-front/test/create-questionnaire1";
    }
    @PostMapping("/creation")
    public void createQuestionnaire(@RequestBody Scoring scoring)
    {

        scoring.setType(TypeOfScoring.QUESTIONER);
        scoring.setDate(new Date());
        System.out.println(scoring.getDate());
        scoringService.save(scoring);
    }
    @GetMapping("/questionnaire/{questionnaireId}/{customerId}")
    public String getQuestionnaire(@PathVariable String questionnaireId,
                                   @PathVariable String customerId,
                                   Model model)
    {
        Scoring scoring = scoringService.findById(questionnaireId);
        System.out.println(scoring.getQuestions().get(0).getQuestionText());
        model.addAttribute("scoring", scoring);
        model.addAttribute("title", scoring.getTitle());
        model.addAttribute("customerId", customerId);
        return "new-front/test/questionnaire";
    }
    @PostMapping("/submit")
    public ResponseEntity<String> submitScoring(@RequestBody CompletedScoring completedScoring) {
        System.out.println("Scoring title: " + completedScoring.getTitle());
        System.out.println("Customer id: " + completedScoring.getCustomerId());
        Customer customer = customerService.findById(completedScoring.getCustomerId());
        Client client = clientService.findById(customer.getId());
        if (client == null)
        {
            System.out.println("creating new client");
            client = new Client();
            client.setId(customer.getId());
            client.setTypeOfClient(customer.getTypeOfClient());
            client.setName(customer.getName());
            client.setSurname(customer.getSurname());
            client.setMiddleName(customer.getLastName()); // Используем lastName как middleName
            client.setContact(customer.getContact());
            client.setFinancialConditions(customer.getFinancialConditions());
            client.setGender(customer.getSex() != null ? convertSexToGender(customer.getSex()) : null);
            client.setBirthDay(customer.getDateOfBirth());
            client.setRecommendations(customer.getCollegialRecommendations());
        }
        client.addScoring(completedScoring);
        clientService.save(client);
        return ResponseEntity.ok("Success");
    }
    @GetMapping("test/creation")
    public String createTestForm(@AuthenticationPrincipal UserDetails userDetails,
                                 Model model)
    {
        Specialist specialist = specialistService.findByUsername(userDetails.getUsername());
        model.addAttribute("specialist", specialist);
        return "new-front/test/create-new-test";
    }
    @PostMapping("test/creation")
    public void createTest(@RequestBody Scoring scoring) {
        System.out.println(scoring.getTitle());
        scoring.setDate(new Date());
        System.out.println(scoring.getDate());
        scoring.getQuestions().forEach(question -> {
            System.out.println("Question: " + question.getQuestionText());
            question.getOptions().forEach(option -> System.out.println("Option: " + option));
        });
        scoring.setType(TypeOfScoring.TEST);
        scoringService.save(scoring);
    }

    @GetMapping("/test/{testId}/{customerId}")
    public String getTest(@PathVariable String testId,
                          @PathVariable String customerId,
                          Model model)
    {
        Scoring scoring = scoringService.findById(testId);
        System.out.println(scoring.getQuestions().get(0).getQuestionText());
        model.addAttribute("scoring", scoring);
        model.addAttribute("title", scoring.getTitle());
        model.addAttribute("customerId", customerId);
        return "new-front/test/questionnaire";
    }
    @GetMapping("/list")
    public String getList(@AuthenticationPrincipal UserDetails userDetails,
                          Model model)
    {
        Specialist specialist = specialistService.findByUsername(userDetails.getUsername());
        List<Scoring> scorings = scoringService.findAll();
        model.addAttribute("scorings", scorings);
        model.addAttribute("specialist", specialist);
        return "new-front/test/customers-tests-list";
    }
    @GetMapping("test/edit/{scoringId}")
    public String testEdit(@AuthenticationPrincipal UserDetails userDetails,
                           Model model,
                           @PathVariable String scoringId)
    {
        Specialist specialist = specialistService.findByUsername(userDetails.getUsername());
        Scoring scoring = scoringService.findById(scoringId);
        if (scoring.getQuestions() == null) {
            scoring.setQuestions(new ArrayList<>());
        }
        model.addAttribute("specialist", specialist);
        model.addAttribute("scoring", scoring);
        return "new-front/test/test-edit";
    }

    @PutMapping("test/{id}/update")
    public String updateTest(@PathVariable String id, @RequestBody Scoring scoring) {
        scoring.setId(id);
        scoring.getQuestions().forEach(question -> System.out.println(question.getQuestionText()));
        scoringService.update(scoring);
        return "redirect:/list"; // перенаправление на список тестов или другой нужный маршрут
    }
    private static Gender convertSexToGender(Sex sex) {
        // Преобразуем Sex в Gender в зависимости от вашей логики
        if (sex == null) {
            return null;
        }
        switch (sex) {
            case FEMALE:
                return Gender.FEMALE;
            default:
                return Gender.MALE;
        }
    }

    @GetMapping("test/copy-link")
    public String getCopyLink() {
        return "new-front/test/copy-link";
    }
}
