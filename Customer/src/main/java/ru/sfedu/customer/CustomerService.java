package ru.sfedu.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sfedu.customer.dto.CustomerDTO;
import ru.sfedu.customer.dto.CustomerDTORepository;
import ru.sfedu.customer.exception.NotFoundException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {


    private CustomerRepository customerRepository;
    private CustomerDTORepository customerDTORepository;
    @Autowired
    public CustomerService(CustomerRepository customerRepository,
                           CustomerDTORepository customerDTORepository) {
        this.customerRepository = customerRepository;
        this.customerDTORepository = customerDTORepository;
    }

    public List<Customer> getCustomers(String name, String someContact) {
        List<Customer> customers = customerRepository.findAllByNameAndSomeContact(name, someContact);
        if (customers.isEmpty()) {
            throw new NotFoundException(
                    String.format("Customer not found with passed name %s and contact %s", name, someContact)
            );
        }
        return customers;
    }

    public List<CustomerDTO> getAllCustomers() throws IOException {
        List<CustomerDTO> customers = customerDTORepository.findAll();
        if (customers.isEmpty()) {
            throw new NotFoundException("No customers in db");
        }
        return customers;
    }

    public Customer findById(String id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) {
            throw new NotFoundException(
                    String.format("Customer not found with id %s", id)
            );
        }
        return customer.get();
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.insert(customer);
    }

    public void deleteCustomer(String id) {
        if (customerRepository.findById(id).isEmpty()) {
            throw new NotFoundException("Customer with id " + id + "hasn't been found");
        }
        customerRepository.deleteById(id);
    }

    public Customer updateCustomer(Customer customer) {
        String id = customer.getId();
        if (customerRepository.findById(id).isEmpty()) {
            throw new NotFoundException("Customer with id " + id + " not found.");
        }
        return customerRepository.save(customer);
    }

}
