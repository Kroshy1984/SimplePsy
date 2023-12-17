package ru.sfedu.simplepsy.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sfedu.simplepsy.NotFoundException;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer getCustomer(String name, String contact) {
        return customerRepository
                .findByNameAndSomeContact(name, contact)
                .orElseThrow(() -> new NotFoundException("Customer not found"));
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.insert(customer);
    }

}
