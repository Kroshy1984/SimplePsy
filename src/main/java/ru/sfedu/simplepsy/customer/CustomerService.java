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

    public void deleteCustomer(String id) {
        if (customerRepository.findById(id).isEmpty()) {
            throw new NotFoundException("Customer with id " + id + " not found.");
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
