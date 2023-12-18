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
    public Customer getResource(@RequestParam(name = "name", required = false) String name,
                                @RequestParam(name = "contact", required = false) String contact) {

        if(name != null && contact == null) return customerService.getCustomerByName(name);
        if(name == null && contact != null) return customerService.getCustomerByContact(contact);
        else return customerService.getCustomerByNameAndContact(name, contact);
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createResource(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }

}