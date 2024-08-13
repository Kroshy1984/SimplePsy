package ru.sfedu.simplepsyspecialist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sfedu.simplepsyspecialist.entity.Customer;
import ru.sfedu.simplepsyspecialist.entity.Problem;
import ru.sfedu.simplepsyspecialist.entity.Scoring;
import ru.sfedu.simplepsyspecialist.service.CustomerService;
import ru.sfedu.simplepsyspecialist.service.ScoringService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
@RequestMapping("/SimplePsy/V1/customer")
public class CustomerController {

    private CustomerService customerService;
    private ScoringService scoringService;

    @Autowired
    public CustomerController(CustomerService customerService, ScoringService scoringService) {
        this.customerService = customerService;
        this.scoringService = scoringService;
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
    public ResponseEntity<String> newCustomer(@RequestBody Customer customer)
    {
        System.out.println("Got the new customer:\n" + customer.getName());
        System.out.println(customer.getContact().getEmail());
        System.out.println(customer.getName());
        System.out.println(customer.getProblemsId());
        String newCustomerId = customerService.saveCustomer(customer).getId();
        System.out.println("CustomerController: " + newCustomerId);
        return ResponseEntity.ok(newCustomerId);
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

    @GetMapping("/update-status")
    public void createCustomers(@RequestParam String customerId) {
        customerService.updateStatus(customerId);
    }

    @GetMapping("/findCustomerByContactData")
    public ResponseEntity<String> findCustomerByContactData(@RequestParam("data") String data)
    {
        try {
            Customer customer = customerService.findByContactData(data);
            return ResponseEntity.ok(customer.getId());
        } catch (NullPointerException | NoSuchElementException e) {
            return ResponseEntity.ok("Customer not found");
        }

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
    public String getClientForm(Model model) {
        model.addAttribute("customerDTO", new Customer());
        return "new-front/customer/customer-creation";
    }

    @GetMapping("/customer-card/{customerId}")
    public String getCustomerCard(@PathVariable String customerId, Model model) {
        System.out.println("In method getCustomerCard got the customerId: " + customerId);
        Customer customer = customerService.findById(customerId);
        customer.setId(customerId);
        model.addAttribute("customer", customer);
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
    public ResponseEntity<String> createNewCustomer(Customer customer) throws IOException {
        //System.out.println("Got the new customer:\n" + name);
        customer.cleanAttributes();
        System.out.println(customer.getSurname());
        System.out.println(customer.getDateOfBirth());
        System.out.println(customer.getSex());
        Customer newCustomer = customerService.saveCustomer(customer);
        System.out.println(newCustomer.getSurname());
        System.out.println(newCustomer.getDateOfBirth());
        System.out.println(newCustomer.getSex());
        return ResponseEntity.ok("Customer " + customer.getName() + " successfully created");
    }
    @GetMapping("/customers-test")
    public String getCustomersTestsList(Model model) {
        List<Scoring> scorings = scoringService.findAll();
        scorings.stream().forEach(scoring -> {
            System.out.println(scoring.getTitle());
            System.out.println(scoring.getType().toString());
        });
        model.addAttribute("scorings", scorings);
        return "new-front/test/customers-tests-list";
    }
}