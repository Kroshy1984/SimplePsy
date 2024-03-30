package ru.sfedu.simplepsy.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.sfedu.simplepsy.customer.document.Contact;
import ru.sfedu.simplepsy.customer.document.Customer;
import ru.sfedu.simplepsy.customer.dto.CustomersSearch;
import ru.sfedu.simplepsy.customer.service.CustomerService;

import java.io.IOException;
import java.util.List;

@RestController
//@RequestMapping("/SimplePsy/V1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.FOUND)
    public List<Customer> getResource(@RequestParam(name = "name", required = false) String name,
                                      @RequestParam(name = "contact", required = false) String contact) {

        return customerService.getCustomers(name, contact);
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createResource(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }

    @PostMapping("/new-customer")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer(@RequestParam("name") String name,
                                   @RequestParam("status") String status,
                                   @RequestParam("contactPhone") String contactPhone,
                                   @RequestParam("contactEmail") String contactEmail,
                                   @RequestParam("contactTg") String contactTg,
                                   @RequestParam("dateOfFirstCall") String dateOfFirstCall,
                                   @RequestParam("avatar") MultipartFile avatar) throws IOException {
        Contact contact = new Contact(contactPhone, contactEmail, contactTg);
        Customer customer = new Customer(name, status, contact, dateOfFirstCall, avatar);
        return customerService.saveCustomer(customer);
    }

    @PostMapping
    @DeleteMapping("/{id}")
    public boolean deleteResource(@PathVariable("id") String id) {
        customerService.deleteCustomer(id);
        return true;
    }

    @PutMapping("/")
    public Customer updateResource(@RequestBody Customer customer) {
        return customerService.updateCustomer(customer);
    }

    @GetMapping("/search2")
    public CustomersSearch searchCustomers(@RequestBody CustomersSearch customersSearch) {
        return customerService.searchCustomers(customersSearch.getSpecialistId(), customersSearch.getCustomers());
    }

    @GetMapping("/create2")
    public CustomersSearch createCustomers(@RequestBody CustomersSearch customersSearch) {
        return customerService.createCustomers(customersSearch.getSpecialistId(), customersSearch.getCustomers());
    }

    @GetMapping("/update-status")
    public void createCustomers(@RequestParam String customerId) {
        customerService.updateStatus(customerId);
    }

}
