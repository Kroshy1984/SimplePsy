package ru.sfedu.scoring.scoring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/SimplePsySpecialist/V1/scoring")
public class ScoringController {

    @GetMapping("/new")
    public String Scoring(Model model)
    {
        model.addAttribute("scoring", new Scoring());
    }
    @PostMapping("/new")
    public String Scoring(Model model)
    {
        model.addAttribute("scoring", new Scoring());
    }
    @GetMapping("/edit")
    public String Scoring(Model model)
    {
        model.addAttribute("scoring", new Scoring());
    }
    @PostMapping("/edit")
    public String Scoring(Model model)
    {
        model.addAttribute("scoring", new Scoring());
    }
    @PostMapping("/answer")
    public String Scoring(Model model)
    {
        model.addAttribute("scoring", new Scoring());
    }
}
