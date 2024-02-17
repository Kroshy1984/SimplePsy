package ru.sfedu.scoring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @GetMapping("/textQuestions")
    public String textQuestions(Model model)
    {
        model.addAttribute("questions", Scoring.getTextQuestions());
        return "textQuestions";
    }

    @GetMapping("/checkboxQuestions")
    public String checkboxQuestions(Model model) {
        model.addAttribute("questions", Scoring.getCheckboxQuestions());
        return "checkboxQuestions";
    }

    @PostMapping("/saveAnswers")
    public String getCheckboxQuestions(@RequestBody String[] answers) {
        this.answers.addAll(List.of(answers));
        scoringService.save(this.answers);
        System.out.println("Received answers: " + Arrays.toString(answers));
        return "redirect:/SimplePsyScoring/V1/scoring/done";
    }

    @GetMapping("/done")
    public String done(Model model)
    {
        return "done";
    }
}
