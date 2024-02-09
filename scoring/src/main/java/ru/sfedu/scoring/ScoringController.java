package ru.sfedu.scoring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;

@Controller
@RequestMapping("/SimplePsyScoring/V1/scoring")
public class ScoringController {

    @GetMapping("/userForm")
    public String userForm(Model model)
    {
        model.addAttribute("questions", Scoring.getUserData());
        return "userForm";
    }
    @PostMapping("/userForm")
    public String getUserForm(@RequestBody String[] answers)
    {
        System.out.println("Received answers: " + Arrays.toString(answers));
        return "redirect:/SimplePsyScoring/V1/scoring/textQuestion";
    }
    @GetMapping("/textQuestion")
    public String textQuestions(Model model)
    {
        model.addAttribute("questions", Scoring.getTextQuestions());
        return "textQuestions";
    }
    @PostMapping(value = "/textQuestion")
    public String getQuestions(@RequestBody String[] answers)
    {
        System.out.println("Received answers: " + Arrays.toString(answers));
        return "redirect:/SimplePsyScoring/V1/scoring/userForm";
    }
    @GetMapping("/checkboxQuestions")
    public String checkboxQuestions(Model model) {
        model.addAttribute("questions", Scoring.getCheckboxQuestions());
        return "checkBoxQuestions";
    }
    @PostMapping("/checkboxQuestions")
    public String getCheckboxQuestions(@RequestBody String[] answers) {
        System.out.println("Received answers: " + Arrays.toString(answers));
        return "redirect:/SimplePsyScoring/V1/scoring/done";
    }
    @GetMapping("/done")
    public String done(Model model)
    {
        return "done";
    }
}
