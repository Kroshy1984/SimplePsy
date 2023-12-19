package ru.sfedu.simplepsy.customer;

import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/SimplePsy/V1")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customer/Search")
    public List<Customer> getResource(@RequestParam(name="name") @NotBlank String name,
                                      @RequestParam(name="contact") @NotBlank String contact) {

        return customerService.getCustomers(name, contact);
    }

    @PostMapping("/customer/new")
    public Customer createResource(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }

    @DeleteMapping("/customer/{id}")
    public boolean deleteResource(@PathVariable("id") String id) {
        customerService.deleteCustomer(id);
        return true;
    }

    @PutMapping("/customer")
    public Customer updateResource(@RequestBody Customer customer) {
        return customerService.updateCustomer(customer);
    }

}