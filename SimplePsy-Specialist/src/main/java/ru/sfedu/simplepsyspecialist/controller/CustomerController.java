package ru.sfedu.simplepsyspecialist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sfedu.simplepsyspecialist.entity.*;
import ru.sfedu.simplepsyspecialist.service.CustomerService;
import ru.sfedu.simplepsyspecialist.service.ScoringService;
import ru.sfedu.simplepsyspecialist.service.SpecialistService;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
@RequestMapping("/SimplePsy/V1/customer")
public class CustomerController {

    private CustomerService customerService;
    private ScoringService scoringService;
    private SpecialistService specialistService;

    @Autowired
    public CustomerController(CustomerService customerService, ScoringService scoringService, SpecialistService specialistService) {
        this.customerService = customerService;
        this.scoringService = scoringService;
        this.specialistService = specialistService;
    }

//    @GetMapping("/search")
//    @ResponseStatus(HttpStatus.FOUND)
//    public List<Customer> getResource(@RequestParam(name = "name", required = false) String name,
//                                      @RequestParam(name = "contact", required = false) String contact) {
//        return customerService.getCustomers(name, contact);
//    }

//    @PostMapping("/new-customer")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Customer createCustomer(@RequestParam("name") String name,
//                                   @RequestParam("surname") String surname,
//                                   @RequestParam("status") Status status,
//                                   @RequestParam("contactPhone") String contactPhone,
//                                   @RequestParam("contactEmail") String contactEmail,
//                                   @RequestParam("contactTg") String contactTg,
//                                   @RequestParam("dateOfFirstCall") String dateOfFirstCall,
//                                   @RequestParam("avatar") MultipartFile avatar) throws IOException {
//        Contact contact = new Contact(contactPhone, contactEmail, contactTg);
//        Customer customer = new Customer(name, status, contact, dateOfFirstCall, avatar);
//        return customerService.saveCustomer(customer);
//    }

    @PostMapping("/new")
    public String newCustomer(@ModelAttribute("customer") Customer customer)
    {
        System.out.println("Got the customer with id:\n" + customer.getId());
        String newCustomerId = customerService.saveCustomer(customer).getId();
        System.out.println("CustomerController: " + newCustomerId);
        return "redirect:/SimplePsy/V1/customer/customer-card/" + newCustomerId;
    }
    @PostMapping("/update")
    public ResponseEntity<String> updateCustomer(@RequestBody Customer customer)
    {
        System.out.println("In method updateCustomer got the customerDTO with id: " + customer.getId());
        customerService.updateCustomer(customer);
        return ResponseEntity.ok("Customer " + customer.getName() + " successfully updated");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteResource(@PathVariable("id") String id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok("Customer " + id + " successfully deleted");
    }

    @GetMapping("/getAllCustomers")
    public ResponseEntity<List<Customer>> getAllCustomers()
    {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }
    @GetMapping("/getAllCustomersWithStatusCustomer")
    public ResponseEntity<List<Customer>> getAllCustomersWithStatusCustomer()
    {
        return ResponseEntity.ok(customerService.getAllCustomersWithStatusCustomer());
    }
    @GetMapping("/getCustomerById")
    public ResponseEntity<Customer> getCustomerById(@RequestParam("customerId") String customerId)
    {
        return ResponseEntity.ok(customerService.findById(customerId));
    }
    @GetMapping("/getCustomerByProblemId")
    public ResponseEntity<Customer> getCustomerByProblemId(@RequestParam("problemId") String problemId)
    {
        System.out.println("In method getCustomerByProblemId got the problemId " + problemId);
        return ResponseEntity.ok(customerService.findByProblemId(problemId));
    }
    @GetMapping("/changeCustomerStatusOnCustomer")
    public ResponseEntity<String> changeCustomerStatusOnCustomer(@RequestParam("problemId") String problemId)
    {
        System.out.println("In method changeCustomerStatusOnCustomer got the problemId " + problemId);
        customerService.changeCustomerStatusOnCustomer(problemId);
        return ResponseEntity.ok("Customer's status successfully changed");
    }
    @GetMapping("/customerToClient")
    public ResponseEntity<Customer> CustomerToClient(@RequestParam("customerId") String customerId)
    {
        customerService.updateStatus(customerId);
        return ResponseEntity.ok(customerService.findById(customerId));
    }
    @DeleteMapping("/deleteCustomerById")
    public ResponseEntity<String> deleteCustomerById(@RequestParam("customerId") String customerId)
    {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.ok("Customer successfully deleted");
    }
    @PostMapping("/problem/new")
    public ResponseEntity<String> customerNewProblem(@RequestParam("customerId") String customerId,
                                                     @RequestParam("problemId") String problemId)
    {
        System.out.println("In method customerNewProblem got the customerId " + customerId + " and problemId " + problemId);
        customerService.addProblem(customerId, problemId);
        return ResponseEntity.ok("Problem successfully added");
    }
    @PostMapping("/problems")
    public ResponseEntity<List<Problem>> customersProblems(@RequestParam("customerId") String customerId)
    {
        System.out.println("In method customersProblems got the customerId " + customerId);
        try {
            List<Problem> problems = customerService.getAllCustomersProblems(customerId);
            return ResponseEntity.ok(problems);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Problems list is empty!");
            return ResponseEntity.ok(new ArrayList<>());
        }
    }
    @GetMapping("/customer-form")
    public String getClientForm(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        Specialist specialist = specialistService.findByUsername(userDetails.getUsername());
        model.addAttribute("customerDTO", new Customer());
        model.addAttribute("specialist", specialist);
        return "new-front/customer/customer-creation";
    }

    @GetMapping("/customer-card/{customerId}")
    public String getCustomerCard(@AuthenticationPrincipal UserDetails userDetails,
                                  @PathVariable String customerId,
                                  Model model) {
        Specialist specialist = specialistService.findByUsername(userDetails.getUsername());
        System.out.println("In method getCustomerCard got the customerId: " + customerId);
        Customer customer = customerService.findById(customerId);
        customer.setId(customerId);
        model.addAttribute("customer", customer);
        model.addAttribute("specialist", specialist);
        switch (customer.getTypeOfClient()) {
            case ADULT -> {
                return "new-front/customer/customer-card-adult";
            }
            case COUPLE -> {
                return "new-front/customer/customer-card-couple";
            }
            case CHILD -> {
                return "new-front/customer/customer-card-child";
            }
        }
        return "new-front/customer/customer-card-adult";
    }

    @PostMapping("/customers/new")
    public String createNewCustomer(@AuthenticationPrincipal UserDetails userDetails,
                                                    Customer customer) {

        customer.cleanAttributes();
        System.out.println(customer.getSurname());
        System.out.println(customer.getDateOfBirth());
        System.out.println(customer.getSex());
        Customer newCustomer = customerService.saveCustomer(customer);
        Specialist specialist = specialistService.findByUsername(userDetails.getUsername());
        specialist.addCustomerId(customer.getId());
        specialistService.save(specialist);
        System.out.println(newCustomer.getSurname());
        System.out.println(newCustomer.getDateOfBirth());
        System.out.println("Возраст: " + newCustomer.getAge());
        System.out.println(newCustomer.getSex());

        return "redirect:/SimplePsy/V1/specialist/customers";
    }
    @GetMapping("/customers-test")
    public String getCustomersTestsList(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        Specialist specialist = specialistService.findByUsername(userDetails.getUsername());
        List<Scoring> scorings = scoringService.findAll();
        scorings.stream().forEach(scoring -> {
            System.out.println(scoring.getTitle());
            System.out.println(scoring.getType().toString());
        });
        model.addAttribute("specialist", specialist);
        model.addAttribute("scorings", scorings);
        return "new-front/test/customers-tests-list";
    }
    @GetMapping("/findAll/{specialistId}")
    public ResponseEntity<List<Customer>> findAllCustomer(@PathVariable String specialistId)
    {
        System.out.println("currentSpecialistId: " + specialistId);
        Specialist specialist = specialistService.findById(specialistId);
        List<String> customerIds = specialist.getCustomerIds();
        List<Customer> customers = new ArrayList<>();
        for (String customerId : customerIds) {
            Customer customer = customerService.findById(customerId);
            if (customer != null) {
                customers.add(customerService.findById(customerId));
            }
        }
        System.out.println("Customers list:");
        customers.stream().forEach(customer -> System.out.println(customer.getTypeOfClient()));
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/scorings/{customerId}")
    public String getCustomersCompletedScoring(@AuthenticationPrincipal UserDetails userDetails,
                                               @PathVariable String customerId,
                                               Model model) {
        Specialist specialist = specialistService.findByUsername(userDetails.getUsername());
        Customer customer = customerService.findById(customerId);
        List<CompletedScoring> completedScorings = customer.getCompletedScorings();
        System.out.println("Completed scorings title:");
        if (completedScorings == null) {
            model.addAttribute("specialist", specialist);
            return "new-front/customer/customers-completed-scorings";
        }
        for (CompletedScoring c : completedScorings) {
            System.out.println(c.getTitle());
        }
        model.addAttribute("specialist", specialist);
        model.addAttribute("completedScorings", completedScorings);
        model.addAttribute("customerId", customerId);
        return "new-front/customer/customers-completed-scorings";
    }
    @GetMapping("/findAll")
    public ResponseEntity<List<Customer>> findAllCustomer()
    {
        List<Customer> customers = customerService.findAll();
        System.out.println("Clients list:");
        customers.stream().forEach(customer -> System.out.println(customer.getTypeOfClient()));
        return ResponseEntity.ok(customers);
    }
    @GetMapping("customer/create/modal")
    public String createCustomerModal(Model model)
    {
        return "new-front/customer/create-customer-modal";
    }
}