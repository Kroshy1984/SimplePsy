package ru.sfedu.simplepsyspecialist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sfedu.simplepsyspecialist.dto.Contact;
import ru.sfedu.simplepsyspecialist.dto.CustomerDTO;
import ru.sfedu.simplepsyspecialist.dto.SessionDTO;
import ru.sfedu.simplepsyspecialist.entity.Specialist;
import ru.sfedu.simplepsyspecialist.service.SpecialistService;

import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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
    public String loginForm(Model model) {
        model.addAttribute("specialist", new Specialist());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("specialist") Specialist specialist) {
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
    public String createNewSpecialist(@ModelAttribute("specialist") Specialist specialist) {
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

    /*@PostMapping("/search")
    public void handleGetRequest(
            @RequestParam("specialist_id") String specialist_id,
            @RequestParam("start_date") String start_date,
            @RequestParam("end_date") String end_date) {
        specialistService.sendRequestToSession(specialist_id, start_date, end_date);
    }*/

    @PostMapping("/calendar")
    public String sendDates(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam("start_date") String startDate,
            @RequestParam("end_date") String endDate
    ) {
        System.out.println(startDate);
        System.out.println(endDate);
        Specialist specialist = specialistService.findByUsername(userDetails.getUsername());
        String specialist_id = specialist.getId();
        specialistService.sendRequestToSession(specialist_id, startDate, endDate);
        return "redirect:/SimplePsySpecialist/V1/specialist/calendar";
    }

    @GetMapping("/session")
    public String sessionForm(Model model) {
        return "session";
    }

    @GetMapping("/sessions")
    public String sessionForm(@RequestParam("specialistId") String specialistId, Model model) {
        List<SessionDTO> sessionDTOS = specialistService.getAllSessions(specialistId);

        // Группируем сессии по дням недели
        Map<DayOfWeek, List<SessionDTO>> meetingsByDay = specialistService.groupSessionsByDay(sessionDTOS);
        // Передаем данные в шаблон
        model.addAttribute("meetingsByDay", meetingsByDay);

        return "sessions";
//        return "redirect:/SimplePsySpecialist/V1/specialist/calendar";
    }

    @PostMapping("/session")
    public String createNewSession(@AuthenticationPrincipal UserDetails userDetails,
                                   @RequestParam("email") String email,
                                   @RequestParam("problem") String problem,
                                   @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        System.out.println(email);
        System.out.println(problem);
        System.out.println(date);
        Specialist specialist = specialistService.findByUsername(userDetails.getUsername());
        System.out.println("Specialist found!");
        String specialist_id = specialist.getId();
        System.out.println(specialist_id);
        specialistService.createNewSession(email, specialist_id, problem, date);
        System.out.println("Session was created");
        return "redirect:/SimplePsySpecialist/V1/specialist/calendar";
    }

    @GetMapping("/formed_calendar")
    public String formed_calendar() {
        return "formed_calendar";
    }

    @GetMapping("/customers")
    public String getCustomersList(Model model) {
        List<CustomerDTO> customers = specialistService.getAllCustomers();
        model.addAttribute("customers", customers);
        return "customer-list";
    }

    @GetMapping("/customer-card/{customerId}")
    public String getCustomerCard(@PathVariable String customerId, Model model) {
        CustomerDTO customer = specialistService.findCustomerById(customerId);
        model.addAttribute("customer", customer);
        return "customer-card";
    }

    @DeleteMapping("/{id}")
    public String deleteResource(@PathVariable("id") String id) {
        specialistService.deleteCustomerById(id);
        return "redirect:/SimplePsy/V1/specialist/customers";
    }

    @PostMapping("/customers/new")
    public ResponseEntity<String> newCustomer(@RequestParam("name") String name,
                                              @RequestParam("surname") String surname,
                                              @RequestParam("dateOfBirth") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOfBirth,
                                              @RequestParam("gender") String gender,
                                              @RequestParam("contact.phone") String phone,
                                              @RequestParam("contact.email") String email,
                                              @RequestParam("contact.tg") String tg,
                                              @RequestParam("problem") String problem) {
        System.out.println("Got the new customer:\n" + name);
        System.out.println(surname);
        System.out.println(dateOfBirth);
        System.out.println(gender);
        System.out.println(phone);
        System.out.println(email);
        System.out.println(tg);
        System.out.println(problem);
        Contact contact = new Contact(phone, email, tg);
        specialistService.saveCustomer(new CustomerDTO(name, surname, dateOfBirth, gender, contact), problem);
        return ResponseEntity.ok("Customer " + name + " successfully saved");
    }

    @GetMapping("/customer-form")
    public String clientForm(Model model) {
        model.addAttribute("customerDTO", new CustomerDTO());
        return "customer-form";
    }


}