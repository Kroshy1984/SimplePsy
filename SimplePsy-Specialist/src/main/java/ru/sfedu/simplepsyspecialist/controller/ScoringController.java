package ru.sfedu.simplepsyspecialist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sfedu.simplepsyspecialist.entity.Client;
import ru.sfedu.simplepsyspecialist.entity.CompletedScoring;
import ru.sfedu.simplepsyspecialist.entity.Scoring;
import ru.sfedu.simplepsyspecialist.service.ClientService;
import ru.sfedu.simplepsyspecialist.service.ScoringService;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
@RequestMapping("/SimplePsy/V1/scoring")
public class ScoringController {

    public List<String> answers = new ArrayList<>();
    ScoringService scoringService;
    ClientService clientService;

    @Autowired
    public ScoringController(ScoringService scoringService, ClientService clientService) {
        this.scoringService = scoringService;
        this.clientService = clientService;
    }

//    @GetMapping("/userForm")
//    public String userForm(Model model)
//    {
//
//        model.addAttribute("questions", Scoring.getUserData());
//        return "userForm";
//    }

    /*@GetMapping("/checkboxQuestions")
    public String checkboxQuestions(Model model) {
        model.addAttribute("checkboxQuestions", Scoring.getCheckboxQuestions());
        return "checkboxQuestions";
    }*/

//    @PostMapping("/saveAnswers")
//    public ResponseEntity<String> saveAnswers(@RequestBody String[] answers,
//                                              @RequestParam("scoringId") String scoringId) {
//        System.out.println("In method saveAnswers got the scoring id " + scoringId);
//        this.answers.clear();
//        this.answers.addAll(List.of(answers));
//        scoringService.save(scoringId, this.answers);
//        return ResponseEntity.ok(scoringId);
////        return "redirect:/SimplePsy/V1/scoring/done";
//    }

    // TODO: Исправить добавление пустых скорингов
    // Возвращаем скоринг
//    @GetMapping("{problemId}")
//    public String getScoring(@PathVariable String problemId,
//                             Model model) {
//        String scoringId = scoringService.saveScoring(new Scoring()).getId();
//        String clientUrl = System.getenv().getOrDefault("CLIENT_SERVICE_URL", "http://localhost:8086");
//        String scoringUrl = System.getenv().getOrDefault("SCORING_SERVICE_URL", "http://localhost:8084");
//        model.addAttribute("clientUrl", clientUrl);
//        model.addAttribute("scoringUrl", scoringUrl);
//        model.addAttribute("scoringId", scoringId);
//        model.addAttribute("problemId", problemId);
//        model.addAttribute("textQuestions", Scoring.getTextQuestions());
//        model.addAttribute("checkboxQuestions", Scoring.getCheckboxQuestions());
//        return "questionnaire";
//    }

    @GetMapping("/done")
    public String done()
    {
        return "done";
    }

    @GetMapping("/result/{id}")
    public ResponseEntity<List<String>> getScoringResult(@PathVariable String id)
    {
        scoringService.getScoringResult();
        return null;
    }

    @PostMapping("/find-customer/byProblemId/{problemId}")
    public ResponseEntity<String> sendProblemId(@PathVariable String problemId,
                                                @RequestParam("scoringId") String scoringId) {
        System.out.println("In method sendProblemId\nGot the problemId " + problemId);
        System.out.println("The scoringId is " + scoringId);
        scoringService.saveCustomersScoring(problemId, scoringId);
        scoringService.sendProblemId(problemId);
        return ResponseEntity.ok("Success");
    }
//    @GetMapping("/getScoringAnswers")
//    public ResponseEntity<List<String>> getScoringAnswers(@RequestParam("scoringId") String scoringId)
//    {
//        System.out.println("In method getScoringAnswers got the scoringId: " + scoringId);
//        List<String> answers = scoringService.getScoringAnswers(scoringId);
//        return ResponseEntity.ok(answers);
//    }
    @GetMapping("/creation")
    public String createQuestionnaireForm()
    {
        return "new-front/test/create-questionnaire1";
    }
    @PostMapping("/creation")
    public String createQuestionnaire(@RequestBody Scoring scoring)
    {
        scoringService.save(scoring);
        return "new-front/test/create-questionnaire1";
    }
    @GetMapping("/questionnaire/{questionnaireId}/{customerId}")
    public String getQuestionnaire(@PathVariable String questionnaireId,
                                   @PathVariable String customerId,
                                   Model model)
    {
        Scoring scoring = scoringService.findById(questionnaireId);
        System.out.println(scoring.getQuestions().get(0).getQuestionText());
        model.addAttribute("scoring", scoring);
        model.addAttribute("customerId", customerId);
        return "new-front/test/questionnaire";
    }
    @PostMapping("/submit")
    public String submitScoring(@RequestBody CompletedScoring completedScoring) {
        Client client = clientService.findById(completedScoring.getCustomerId());
        client.addScoring(completedScoring);
        clientService.save(client);
        return "new-front/test/create-questionnaire1";
    }
}
