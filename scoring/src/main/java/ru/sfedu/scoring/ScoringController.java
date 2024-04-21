package ru.sfedu.scoring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
@RequestMapping("/SimplePsyScoring/V1/scoring")
public class ScoringController {

    public List<String> answers = new ArrayList<>();
    ScoringService scoringService;

    @Autowired
    public ScoringController(ScoringService scoringService) {
        this.scoringService = scoringService;
    }

    @GetMapping("/userForm")
    public String userForm(Model model)
    {
        model.addAttribute("questions", Scoring.getUserData());
        return "userForm";
    }

    /*@GetMapping("/checkboxQuestions")
    public String checkboxQuestions(Model model) {
        model.addAttribute("checkboxQuestions", Scoring.getCheckboxQuestions());
        return "checkboxQuestions";
    }*/

    @PostMapping("/saveAnswers")
    public ResponseEntity<String> saveAnswers(@RequestBody String[] answers) {
        this.answers.clear();
        this.answers.addAll(List.of(answers));
        scoringService.save(this.answers);
        return ResponseEntity.ok("Success");
//        return "redirect:/SimplePsyScoring/V1/scoring/done";
    }

    // Возвращаем скоринг
    @GetMapping("{id}")
    public String getScoring(@PathVariable String id,
                             Model model) {
        model.addAttribute("customerId", id);
        model.addAttribute("textQuestions", Scoring.getTextQuestions());
        model.addAttribute("checkboxQuestions", Scoring.getCheckboxQuestions());
        return "questionnaire";
    }

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

    @PostMapping("/find-customer/{customerId}")
    public ResponseEntity<String> sendCustomerId(@PathVariable String customerId) {
        scoringService.sendCustomerId(customerId);
        return ResponseEntity.ok("Success");
    }
}
