package ru.sfedu.simplepsy.specialist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/SimplePsy/V1/specialist")
public class SpecialistController {

    @Autowired
    SpecialistService specialistService;

    @GetMapping("/login")
    public String loginPage()
    {
        return "login.html";
    }

    @PostMapping("/new")
    @ResponseBody
    public void createNewSpecialist(@RequestBody Specialist specialist)
    {
        specialistService.save(specialist);
    }
}
