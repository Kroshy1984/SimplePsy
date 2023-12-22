package ru.sfedu.simplepsy.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sfedu.simplepsy.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getCustomers(String name, String someContact) {
        List<Customer> customers = customerRepository.findAllByNameAndSomeContact(name, someContact);
        if (customers.isEmpty()) {
            throw new NotFoundException(
                    String.format("Customer not found with passed name %s and contact %s", name, someContact)
            );
        }
        return customers;
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
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
//    public Customer getCustomerByNameAndContact(String name, String contact) {
//        return customerRepository
//                .findByNameAndSomeContact(name, contact)
//                .orElseThrow(() -> new NotFoundException("Customer not found"));
//    }
//    public Customer getCustomerByName(String name)
//    {
//        return customerRepository
//                .findCustomerByName(name)
//                .orElseThrow(() -> new NotFoundException(String.format("Customer with name %s not found", name)));
//    }
//    public Customer getCustomerByContact(String contact)
//    {
//        return customerRepository
//                .findCustomerByContact(contact)
//                .orElseThrow(() -> new NotFoundException(String.format("Customer with contact %s not found", contact)));
//    }

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
