package ru.sfedu.simplepsyspecialist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sfedu.simplepsyspecialist.entity.Specialist;
import ru.sfedu.simplepsyspecialist.service.SpecialistService;

@Controller
@RequestMapping("/SimplePsySpecialist/V1/specialist")
public class SpecialistController {

    @Autowired
    SpecialistService specialistService;
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<Specialist> getSpecialist(@PathVariable String id) {
        Specialist specialist = specialistService.findById(id);
        if (specialist != null) {
            return ResponseEntity.ok(specialist);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/signup")
    public String signUpForm(Model model) {
        model.addAttribute("specialist", new Specialist());
        return "signup";
    }
    @GetMapping("/login")
    public String loginForm(Model model)
    {
        model.addAttribute("specialist", new Specialist());
        return "login";
    }
    @PostMapping("/login")
    public String login(@ModelAttribute("specialist") Specialist specialist)
    {
        System.out.println(specialist.getUsername());
        System.out.println(specialist.getPassword());
        specialistService.authorizeSpecialist(specialist);
        return "redirect:/SimplePsySpecialist/V1/specialist/calendar";
    }
//@PostMapping("/login")
//public String login(@ModelAttribute("specialist") Specialist specialist)
//{
//    System.out.println(specialist.getEmail());
//    System.out.println(specialist.getPassword());
//    specialistService.authorizeSpecialist(specialist);
//    return "redirect:/SimplePsySpecialist/V1/specialist/calendar";
//}
    @PostMapping("/signup")
    public String createNewSpecialist(@ModelAttribute("specialist") Specialist specialist)
    {
        System.out.println(specialist.getName());
        System.out.println(specialist.getSurname());
        System.out.println(specialist.getUsername());
        System.out.println(specialist.getPassword());
        specialistService.registerNewSpecialist(specialist);
        return "redirect:/SimplePsySpecialist/V1/specialist/calendar";
    }

    @GetMapping("/calendar")
    public String calendar() {
        return "calendar";
    }
}
