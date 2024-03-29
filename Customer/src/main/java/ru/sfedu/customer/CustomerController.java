package ru.sfedu.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.sfedu.customer.dto.CustomerDTO;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/SimplePsy/V1/customer")
public class CustomerController {


    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.FOUND)
    public List<Customer> getResource(@RequestParam(name = "name", required = false) String name,
                                      @RequestParam(name = "contact", required = false) String contact) {

        return customerService.getCustomers(name, contact);
    }

    @PostMapping("/new-customer")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer(@RequestParam("name") String name,
                                   @RequestParam("status") Status status,
                                   @RequestParam("contactPhone") String contactPhone,
                                   @RequestParam("contactEmail") String contactEmail,
                                   @RequestParam("contactTg") String contactTg,
                                   @RequestParam("dateOfFirstCall") String dateOfFirstCall,
                                   @RequestParam("avatar") MultipartFile avatar) throws IOException {
        Contact contact = new Contact(contactPhone, contactEmail, contactTg);
        Customer customer = new Customer(name, status, contact, dateOfFirstCall, avatar);
        return customerService.saveCustomer(customer);
    }

    @PostMapping("/new")
    public ResponseEntity<String> newCustomer(@RequestBody CustomerDTO customer)
    {
        System.out.println("Got the new customer:\n" +customer.getName());
        System.out.println(customer.getStatus());
        System.out.println(customer.getContact().getEmail());
        System.out.println(customer.getName());
        customerService.saveCustomer(customer);
        return ResponseEntity.ok("Customer " + customer.getName() + " successfully created");
    }

    @DeleteMapping("/{id}")
    public boolean deleteResource(@PathVariable("id") String id) {
        customerService.deleteCustomer(id);
        return true;
    }

    @PutMapping("/update")
    public Customer updateResource(@RequestBody Customer customer) {
        return customerService.updateCustomer(customer);
    }

    @GetMapping("/getAllCustomers")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers()
    {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }
    @GetMapping("/getCustomerById")
    public ResponseEntity<CustomerDTO> getCustomerById(@RequestParam("customerId") String customerId)
    {
        return ResponseEntity.ok(customerService.findById(customerId));
    }
    @DeleteMapping("/deleteCustomerById")
    public ResponseEntity<String> deleteCustomerById(@RequestParam("customerId") String customerId)
    {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.ok("Customer successfully deleted");
    }

}