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
import ru.sfedu.simplepsyspecialist.dto.CustomerDTO;
import ru.sfedu.simplepsyspecialist.entity.Session;
import ru.sfedu.simplepsyspecialist.entity.Specialist;
import ru.sfedu.simplepsyspecialist.service.SpecialistService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

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
    )
    {
        System.out.println(startDate);
        System.out.println(endDate);
        Specialist specialist = specialistService.findByUsername(userDetails.getUsername());
        String specialist_id = specialist.getId();
        specialistService.sendRequestToSession(specialist_id, startDate, endDate);
        return "redirect:/SimplePsySpecialist/V1/specialist/calendar";
    }

    @GetMapping("/session")
    public String sessionForm(Model model) {
        model.addAttribute("session", new Session());
        return "session";
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
        String specialist_id = specialist.getId();
        specialistService.createNewSession(email, specialist_id, problem, date);
        return "redirect:/SimplePsySpecialist/V1/specialist/calendar";
    }
    @GetMapping("/formed_calendar")
    public String formed_calendar()
    {
        return "formed_calendar";
    }
    @GetMapping("/customers")
    public String getCustomersList(Model model)
    {
        List<CustomerDTO> customers = specialistService.getAllCustomers();
        model.addAttribute("customers", customers);
        return "customer-list";
    }

//    @GetMapping("/customer-avatar/{customerId}")
//    public ResponseEntity<byte[]> getCustomerAvatar(@PathVariable String customerId) {
//        Customer customer = customerService.findById(customerId);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.IMAGE_PNG);
//        return new ResponseEntity<>(customer.getAvatar(), headers, HttpStatus.OK);
//    }
//    @PostMapping("/customer-card/save")
//    public String saveNewCustomerCard(@ModelAttribute("customer") Customer customer,
//                                      @RequestParam String customerId,
//                                      Model model)
//    {
//        customer.setId(customerId);
//        // это строка затычка. Нужно продумать как передавать
//        // фото для аватара из шаблона
//        customer.setByteAvatar(customerService.findById(customerId).getAvatar());
//        Customer cust = customerService.updateCustomer(customer);
//        return "redirect:/SimplePsy/V1/specialist/customer-card/" + customerId;
//    }

    @GetMapping("/questionnaire")
    public String questionnaire()
    {
        return "questionnaire";
    }

    @PostMapping("/saveAnswers")
    public String saveAnswers(@RequestBody String[] answers) {
        System.out.println("Received answers: " + Arrays.toString(answers));
        return "redirect:/SimplePsy/V1/specialist/questionnaire";
    }

    @DeleteMapping("/{id}")
    public String deleteResource(@PathVariable("id") String id) {
        specialistService.deleteCustomer(id);
        return "redirect:/SimplePsy/V1/specialist/customers";
    }
}
