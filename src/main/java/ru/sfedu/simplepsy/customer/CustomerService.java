package ru.sfedu.simplepsy.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sfedu.simplepsy.exception.NotFoundException;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer getCustomerByNameAndContact(String name, String contact) {
        return customerRepository
                .findByNameAndSomeContact(name, contact)
                .orElseThrow(() -> new NotFoundException("Customer not found"));
    }
    public Customer getCustomerByName(String name)
    {
        return customerRepository
                .findCustomerByName(name)
                .orElseThrow(() -> new NotFoundException(String.format("Customer with name %s not found", name)));
    }
    public Customer getCustomerByContact(String contact)
    {
        return customerRepository
                .findCustomerByContact(contact)
                .orElseThrow(() -> new NotFoundException(String.format("Customer with contact %s not found", contact)));
    }
    public Customer saveCustomer(Customer customer) {
        return customerRepository.insert(customer);
    }

}
