package ru.sfedu.simplepsyspecialist.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sfedu.simplepsyspecialist.dto.Contact;
import ru.sfedu.simplepsyspecialist.dto.CustomerDTO;
import ru.sfedu.simplepsyspecialist.dto.ProblemDTO;
import ru.sfedu.simplepsyspecialist.dto.SessionDTO;
import ru.sfedu.simplepsyspecialist.entity.Scoring;
import ru.sfedu.simplepsyspecialist.entity.Specialist;
import ru.sfedu.simplepsyspecialist.service.SpecialistService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = "*", allowedHeaders = "*")
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
    public String getSignUpForm(Model model) {
        model.addAttribute("specialist", new Specialist());
        return "signup";
    }

    @PostMapping("/signup")
    public String createNewSpecialist(@ModelAttribute("specialist") Specialist specialist) {
        System.out.println(specialist.getName());
        System.out.println(specialist.getSurname());
        System.out.println(specialist.getUsername());
        System.out.println(specialist.getPassword());
        specialistService.registerNewSpecialist(specialist);
        return "redirect:/SimplePsySpecialist/V1/specialist/sessions";
    }

    @GetMapping("/login")
    public String getLoginForm(Model model) {
        model.addAttribute("specialist", new Specialist());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("specialist") Specialist specialist) {
        System.out.println(specialist.getUsername());
        System.out.println(specialist.getPassword());
        specialistService.authorizeSpecialist(specialist);
        return "redirect:/SimplePsySpecialist/V1/specialist/sessions";
    }

//@PostMapping("/login")
//public String login(@ModelAttribute("specialist") Specialist specialist)
//{
//    System.out.println(specialist.getEmail());
//    System.out.println(specialist.getPassword());
//    specialistService.authorizeSpecialist(specialist);
//    return "redirect:/SimplePsySpecialist/V1/specialist/calendar";
//}

    @GetMapping("/calendar")
    public String getCalendar() {
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
    public String getSessionForm(Model model) {
        String specUrl = System.getenv().getOrDefault("SPECIALIST_SERVICE_URL", "http://localhost:8081");
        model.addAttribute("specUrl", specUrl);
        return "session";
    }

    @GetMapping("/sessions")
    public String getSessionForm(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        Specialist specialist = specialistService.findByUsername(userDetails.getUsername());
        List<SessionDTO> sessionDTOS = specialistService.getAllSessions(specialist.getId());
        String specUrl = System.getenv().getOrDefault("SPECIALIST_SERVICE_URL", "http://localhost:8081");
        List<List<SessionDTO>> meetingsByDay = specialistService.groupSessionsByDay(sessionDTOS);
        List<SessionDTO> meetingsByMonday = meetingsByDay.get(0);
        System.out.println(meetingsByMonday.size());
        List<SessionDTO> meetingsByDayTuesday = meetingsByDay.get(1);
        System.out.println(meetingsByDayTuesday.size());
        List<SessionDTO> meetingsByDayWednesday = meetingsByDay.get(2);
        System.out.println(meetingsByDayWednesday.size());
        List<SessionDTO> meetingsByDayThursday = meetingsByDay.get(3);
        System.out.println(meetingsByDayThursday.size());
        List<SessionDTO> meetingsByDayFriday = meetingsByDay.get(4);
        System.out.println(meetingsByDayFriday.size());
        List<SessionDTO> meetingsByDaySaturday = meetingsByDay.get(5);
        System.out.println(meetingsByDaySaturday.size());
        List<SessionDTO> meetingsByDaySunDay = meetingsByDay.get(6);
        System.out.println(meetingsByDaySunDay.size());
        model.addAttribute("meetingsByMonday", meetingsByMonday);
        model.addAttribute("meetingsByDayTuesday", meetingsByDayTuesday);
        model.addAttribute("meetingsByDayWednesday", meetingsByDayWednesday);
        model.addAttribute("meetingsByDayThursday", meetingsByDayThursday);
        model.addAttribute("meetingsByDayFriday", meetingsByDayFriday);
        model.addAttribute("meetingsByDaySaturday", meetingsByDaySaturday);
        model.addAttribute("meetingsByDaySunDay", meetingsByDaySunDay);
        System.out.println(specUrl);
        model.addAttribute("specUrl", specUrl);

        return "sessions";
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
        return "redirect:/SimplePsySpecialist/V1/specialist/sessions";
    }

    @GetMapping("/customers")
    public String getCustomersList(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        Specialist specialist = specialistService.findByUsername(userDetails.getUsername());
        List<CustomerDTO> customers = specialistService.getAllCustomers();
        List<CustomerDTO> specialistCustomers = new ArrayList<>();

        if (specialist.getCustomerIds() == null) {
            System.out.println("No customers were found for this specialist!");
            model.addAttribute("customers", specialistCustomers);
            return "customer-list";
        }

        for (int i = 0; i < specialist.getCustomerIds().size(); i++) {
            for (CustomerDTO customer : customers) {
                if (Objects.equals(customer.getId(), specialist.getCustomerIds().get(i))) {
                    specialistCustomers.add(customer);
                    System.out.println(customers.get(i).getName());
                    break;
                }
            }
        }
        String specUrl = System.getenv().getOrDefault("SPECIALIST_SERVICE_URL", "http://localhost:8081");
        model.addAttribute("specUrl", specUrl);
        model.addAttribute("customers", specialistCustomers);
        return "customer-list";
    }
    @GetMapping("/clients")
    public String getClientsList(@AuthenticationPrincipal UserDetails userDetails, Model model)
    {
        Specialist specialist = specialistService.findByUsername(userDetails.getUsername());
        List<CustomerDTO> customers = specialistService.getAllCustomersWithStatusCustomer();
        List<CustomerDTO> clients = new ArrayList<>();

        if (specialist.getCustomerIds() == null) {
            System.out.println("No customers were found for this specialist!");
            model.addAttribute("customers", customers);
            return "customer-list";
        }

        for (int i = 0; i < specialist.getCustomerIds().size(); i++) {
            for (CustomerDTO customer : customers) {
                if (Objects.equals(customer.getId(), specialist.getCustomerIds().get(i))) {
                    clients.add(customer);
                    System.out.println(customer.getName());
                    break;
                }
            }
        }
        model.addAttribute("clients", clients);
        return "client-list";
    }
    @GetMapping("/customer-card/{customerId}")
    public String getCustomerCard(@PathVariable String customerId, Model model) {
        System.out.println("In method getCustomerCard got the customerId: " + customerId);
        CustomerDTO customer = specialistService.findCustomerById(customerId);
        String specUrl = System.getenv().getOrDefault("SPECIALIST_SERVICE_URL", "http://localhost:8081");
        customer.setId(customerId);
        model.addAttribute("specUrl", specUrl);
        model.addAttribute("customer", customer);
        return "customer-card";
    }

    @PostMapping("/customer-card/update")
    public String updateCustomerCard(@ModelAttribute("customer") CustomerDTO customerDTO,
                                     @RequestParam("customerId") String customerId) {
        System.out.println("In method updateCustomerCard got the customer with name and surname: " + customerDTO.getName() + " " + customerDTO.getSurname());
        System.out.println("Customer's id: " + customerId);
        customerDTO.setId(customerId);
        specialistService.updateCustomer(customerDTO);
        return "redirect:/SimplePsySpecialist/V1/specialist/customer-card/" + customerDTO.getId();
    }

    @PostMapping("/delete-customer/{id}")
    public String deleteResource(@PathVariable("id") String customerId,
                                 @AuthenticationPrincipal UserDetails userDetails) {
        String specialistId = specialistService.findByUsername(userDetails.getUsername()).getId();
        specialistService.deleteCustomerById(customerId, specialistId);
        return "redirect:/SimplePsySpecialist/V1/specialist/customers";
    }

    @PostMapping("/customers/new")
    public String createNewCustomer(@RequestParam("name") String name,
                                    @RequestParam("surname") String surname,
                                    @RequestParam("dateOfBirth") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOfBirth,
                                    @RequestParam("gender") String gender,
                                    @RequestParam("contact.phone") String phone,
                                    @RequestParam("contact.email") String email,
                                    @RequestParam("contact.tg") String tg,
                                    @RequestParam("problem") String problem,
                                    @AuthenticationPrincipal UserDetails userDetails) {
        System.out.println("Got the new customer:\n" + name);
        System.out.println(surname);
        System.out.println(dateOfBirth);
        System.out.println(gender);
        System.out.println(phone);
        System.out.println(email);
        System.out.println(tg);
        System.out.println(problem);
        Contact contact = new Contact(phone, email, tg);
        String customerId = specialistService.saveCustomer(new CustomerDTO(name, surname, dateOfBirth, gender, contact), problem);
        Specialist specialist = specialistService.findByUsername(userDetails.getUsername());
        specialist.addCustomerId(customerId);
        specialistService.save(specialist);
        return "redirect:/SimplePsySpecialist/V1/specialist/customers";
    }

    @GetMapping("/customer-form")
    public String getClientForm(Model model) {
        model.addAttribute("customerDTO", new CustomerDTO());
        return "customer-form";
    }

    @GetMapping("/find-customer/byProblemId")
    public ResponseEntity<String> sendEmailToSpecialistByProblemId(@RequestParam("problemId") String problemId) {
        System.out.println("In method sendEmailToSpecialistByProblemId \nGot problemId: " + problemId);
        String customerId = specialistService.findCustomerByProblemId(problemId).getId();
        Specialist specialist = specialistService.findSpecialist(customerId);
        System.out.println("Found the specialist " + specialist.getName() + " who contains provided customerId");
        String customerName = specialistService.findCustomerById(customerId).getName();
        System.out.println("Found the customer : " + customerName);
        System.out.println("Specialist's email: " + specialist.getUsername());
        System.out.println("sending email to the specialist about scoring completion");
        specialistService.sendEmailtoSpecialist(specialist.getUsername(), specialist.getName(), customerName, problemId);
        return ResponseEntity.ok("Success");
    }

    @GetMapping("/find-customer-form")
    public String getFindCustomerForm() {
        return "find-customer";
    }

    @PostMapping("/find-customer-form")
    public String getCustomerByContactData(@AuthenticationPrincipal UserDetails userDetails,
                                           @RequestParam("data") String data) {
        String customerId = specialistService.findCustomerByContactData(data);
        Specialist specialist = specialistService.findByUsername(userDetails.getUsername());

        if (Objects.equals(customerId, "Customer not found")) {
            return "redirect:/SimplePsySpecialist/V1/specialist/customer-form";
        }

        for (int i = 0; i < specialist.getCustomerIds().size(); i++) {
            if (Objects.equals(customerId, specialist.getCustomerIds().get(i))) {
                return "redirect:/SimplePsySpecialist/V1/specialist/customer-card/" + customerId;
            }
        }
        return "redirect:/SimplePsySpecialist/V1/specialist/customer-form";
    }

    @GetMapping("customer/problem/new/{customerId}")
    public String customerNewProblem(@PathVariable String customerId,
                                     Model model)
    {
        System.out.println("In method customerNewProblem providing customerId " + customerId + " to the model");
        model.addAttribute("customerId", customerId);
        return "problem-form";
    }

    @PostMapping("customer/problem/new")
    public String customerNewProblem(@RequestParam("customerId") String customerId,
                                     @RequestParam("problem") String problem)
    {
        System.out.println("In Post mappping method customerNewProblem \ngot customerId: " + customerId + " and problem: " + problem);
        specialistService.addCustomerProblem(customerId, problem);
        return "redirect:/SimplePsySpecialist/V1/specialist/customer/problems/" + customerId;
    }

    @GetMapping("customer/problems/{customerId}")
    public String customersProblems(@PathVariable String customerId,
                                    Model model)
    {
        System.out.println("In Get mappping method customersProblems \ngot customerId: " + customerId);
        List<ProblemDTO> problems = specialistService.getAllCustomersProblems(customerId);
        String scoringUrl = System.getenv().getOrDefault("SCORING_SERVICE_URL", "http://localhost:8084");
        model.addAttribute("scoringUrl", scoringUrl);
        model.addAttribute("problems", problems);
        return "problems-list";
    }

    @GetMapping("/customer/scoring/{problemId}")
    public String scoringAnswers(@PathVariable String problemId,
                                 Model model)
    {
        System.out.println("In method scoringAnswers");
        List<String> textQuestions = Scoring.getTextQuestions();
        List<String> checkboxQuestions = Scoring.getCheckboxQuestions();
        List<String> answers = specialistService.getScoringAnswersByProblemId(problemId);
        if (answers == null) {
            return "scoring-error";
        }

        LinkedHashMap<String, String> textQuestionsAnswers = new LinkedHashMap<>();
        for (int i = 0; i < textQuestions.size(); i++) {
            textQuestionsAnswers.put(textQuestions.get(i), answers.get(i+1));
        }

        LinkedHashMap<String, String> checkboxQuestionsAnswers = new LinkedHashMap<>();
        for (int i = 0; i < checkboxQuestions.size(); i++) {
            checkboxQuestionsAnswers.put(checkboxQuestions.get(i), answers.get(i+15));
        }

        String customerId = specialistService.findCustomerByProblemId(problemId).getId();

        model.addAttribute("problemId", problemId);
        model.addAttribute("customerId", customerId);
        model.addAttribute("textQuestionsAnswers", textQuestionsAnswers);
        model.addAttribute("checkboxQuestionsAnswers", checkboxQuestionsAnswers);
        return "scoring-answers";
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/SimplePsySpecialist/V1/specialist/login";
    }
    @GetMapping("/scoring/approve/{problemId}")
    public String approveScoring(@PathVariable("problemId") String problemId)
    {
        System.out.println("In method approveScoring got the problemId: " + problemId);
        specialistService.approveScoring(problemId);
        return "redirect:/SimplePsySpecialist/V1/specialist/clients";
    }

}