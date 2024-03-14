package ru.sfedu.simplepsy.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sfedu.simplepsy.customer.document.Customer;
import ru.sfedu.simplepsy.customer.dto.CustomersSearch;
import ru.sfedu.simplepsy.customer.exception.CustomerNotFoundException;
import ru.sfedu.simplepsy.customer.repository.CustomerRepository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getCustomers(String name, String someContact) {
        List<Customer> customers = customerRepository.findAllByNameOrSomeContact(name, someContact);
        if (customers.isEmpty()) {
            throw new CustomerNotFoundException(
                    String.format("Customer not found with passed name %s and contact %s", name, someContact)
            );
        }
        return customers;
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        if (customers.isEmpty()) {
            throw new CustomerNotFoundException("No customers in db");
        }
        return customers;
    }

    public Customer findById(String id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) {
            throw new CustomerNotFoundException(
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
            throw new CustomerNotFoundException("Customer with id " + id + " not found.");
        }
        customerRepository.deleteById(id);
    }

    public Customer updateCustomer(Customer customer) {
        String id = customer.getId();
        if (customerRepository.findById(id).isEmpty()) {
            throw new CustomerNotFoundException("Customer with id " + id + " not found.");
        }
        return customerRepository.save(customer);
    }

    //65af431d9b7b25354b377d6a
    public CustomersSearch searchCustomers(String specialistId, Set<Customer> customers) {
        Set<Customer> foundCustomers = new HashSet<>();

        customers.forEach(customer -> {
            foundCustomers.addAll(customerRepository.findAllByName(customer.getName()));
            foundCustomers.addAll(customerRepository.findAllBySomeContact(customer.getContact().getPhone()));
            foundCustomers.addAll(customerRepository.findAllBySomeContact(customer.getContact().getTg()));
            foundCustomers.addAll(customerRepository.findAllBySomeContact(customer.getContact().getEmail()));
        });

        return new CustomersSearch(specialistId, foundCustomers);
    }

    public CustomersSearch createCustomers(String specialistId, Set<Customer> customers) {
        List<Customer> savedCustomers = customerRepository.saveAll(customers);
        return new CustomersSearch(specialistId, new HashSet<>(savedCustomers));
    }

    public void updateStatus(String customerId) {
        Customer foundCustomer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(""));
        foundCustomer.setStatus("клиент");
    }

    public Customer updateFirstContact(String customerId) {
        Customer foundCustomer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(""));
        foundCustomer.setDateOfFirstCall(LocalDateTime.now().toString());
        return customerRepository.save(foundCustomer);
    }

}
