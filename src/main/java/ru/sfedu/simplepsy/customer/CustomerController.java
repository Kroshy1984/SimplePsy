package ru.sfedu.simplepsy.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/SimplePsy/V1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.FOUND)
    public Customer getResource(@RequestParam("name") String name, @RequestParam("contact") String contact) {
        return customerService.getCustomer(name, contact);
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createResource(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }

    @DeleteMapping("/{id}")
    public boolean deleteResource(@PathVariable("id") String id) {
        customerService.deleteCustomer(id);
        return true;
    }

    @PutMapping("/")
    public boolean updateResource(@RequestBody Customer customer) {
        customerService.updateCustomer(customer);
        return true;
    }


}