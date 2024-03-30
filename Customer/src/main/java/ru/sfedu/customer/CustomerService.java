package ru.sfedu.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sfedu.customer.dto.CustomerDTO;
import ru.sfedu.customer.dto.CustomerMapper;
import ru.sfedu.customer.dto.CustomersSearch;
import ru.sfedu.customer.exception.CustomerNotFoundException;
import ru.sfedu.customer.exception.NotFoundException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
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

    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        System.out.println("List of customers names:");
        for (int i = 0; i < customers.size(); i++) {
            System.out.println(customers.get(i));
        }
        CustomerDTO customerDT = CustomerMapper.INSTANCE.customerToCustomerDTO(customers.get(0));
        System.out.println("customerDTO name: " + customerDT.getName());
        if (customers.isEmpty()) {
            throw new NotFoundException("No customers in db");
        }
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        System.out.println("sending full list of customers to specialist");
        for (int i = 0; i < customers.size(); i++) {
            CustomerDTO customerDTO = CustomerMapper.INSTANCE.customerToCustomerDTO(customers.get(i));
            customerDTOList.add(customerDTO);
            System.out.println(customers.get(i).getName());
        }

        return customerDTOList;
    }

    public CustomerDTO findById(String id) {
        Customer customer = customerRepository.findById(id).get();
        if (customer == null) {
            throw new NotFoundException(String.format("Customer not found with id %s", id));
        }
        else
        {
            System.out.println(String.format("Found the customer with id and name:\n%s: %s",
                    customer.getId(), customer.getName()));
        }
        CustomerDTO customerDTO = CustomerMapper.INSTANCE.customerToCustomerDTO(customer);
        return customerDTO;
    }

    public Customer saveCustomer(Customer customer) {
        System.out.println("saving customer " + customer.getName());
        return customerRepository.insert(customer);
    }
    public Customer saveCustomer(CustomerDTO customerDTO) {
        Customer customer = CustomerMapper.INSTANCE.customerDTOToCustomer(customerDTO);
        System.out.println("Customer CustomerDTO" + customer.getProblemId());
        System.out.println("saving customer " + customer.getName());
        return customerRepository.save(customer);
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
        foundCustomer.setStatus(Status.CUSTOMER);
    }

}
