package ru.sfedu.simplepsyspecialist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sfedu.simplepsyspecialist.entity.Customer;
import ru.sfedu.simplepsyspecialist.entity.Problem;
import ru.sfedu.simplepsyspecialist.entity.nested.Status;
import ru.sfedu.simplepsyspecialist.exception.NotFoundException;
import ru.sfedu.simplepsyspecialist.repo.CustomerRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;
    ProblemService problemService;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, ProblemService problemService) {
        this.customerRepository = customerRepository;
        this.problemService = problemService;
    }

//    public List<Customer> getCustomers(String name, String someContact) {
//        List<Customer> customers = customerRepository.findAllByNameAndSomeContact(name, someContact);
//        if (customers.isEmpty()) {
//            throw new NotFoundException(
//                    String.format("Customer not found with passed name %s and contact %s", name, someContact)
//            );
//        }
//        return customers;
//    }
    public List<Customer> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        if (customers.isEmpty()) {
            throw new NotFoundException(
                    String.format("Customers table is empty")
            );}
        return customers;
    }

    public Customer findById(String id) {
       return customerRepository.findById(id).get();
    }

    public Customer saveCustomer(Customer customer) {
        System.out.println("saving customer " + customer.getName());
        return customerRepository.save(customer);
    }

//    public Customer saveCustomer(CustomerDTO customerDTO) {
//        Customer customer = CustomerMapper.INSTANCE.customerDTOToCustomer(customerDTO);
//        customer.setStatus(Status.LEAD);
//        System.out.println("Customer CustomerDTO" + customer.getProblemsId());
//        System.out.println("saving customer " + customer.getName());
//        return customerRepository.save(customer);
//    }

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
        Customer oldCustomer = customerRepository.findById(id).get();
        customer.setProblemsId(oldCustomer.getProblemsId());
        customer.setStatus(oldCustomer.getStatus());
        return customerRepository.save(customer);
    }

    //65af431d9b7b25354b377d6a
//    public CustomersSearch searchCustomers(String specialistId, Set<Customer> customers) {
//        Set<Customer> foundCustomers = new HashSet<>();
//
//        customers.forEach(customer -> {
//            foundCustomers.addAll(customerRepository.findAllByName(customer.getName()));
//            foundCustomers.addAll(customerRepository.findAllBySomeContact(customer.getContact().getPhone()));
//            foundCustomers.addAll(customerRepository.findAllBySomeContact(customer.getContact().getTg()));
//            foundCustomers.addAll(customerRepository.findAllBySomeContact(customer.getContact().getEmail()));
//        });
//
//        return new CustomersSearch(specialistId, foundCustomers);
//    }
//
//    public CustomersSearch createCustomers(String specialistId, Set<Customer> customers) {
//        List<Customer> savedCustomers = customerRepository.saveAll(customers);
//        return new CustomersSearch(specialistId, new HashSet<>(savedCustomers));
//    }

    public void updateStatus(String customerId) {
        Customer customer = customerRepository.findById(customerId).get();//.orElseThrow(() -> new CustomerNotFoundException(""));
        customer.setStatus(Status.CUSTOMER);
        System.out.println("customer's status " + customer.getName() + " was updated to CUSTOMER");
        customerRepository.save(customer);
    }

    public Customer findByContactData(String data)
    {
        if (isPhoneNumber(data)) {
            if (data.charAt(0) == ' ') {
                data = data.replace(' ', '+');
            }
            return customerRepository.findByContactPhone(data).get();
        } else if (isEmail(data)) {
            return  customerRepository.findByContactEmail(data).get();
        } else if (isTelegramUsername(data)) {
            return customerRepository.findByContactTg(data).get();
        } else {
            System.out.println(data + " - не является ни номером телефона, ни адресом электронной почты, ни никнеймом в Telegram.");
            return null;
        }
    }

    // Проверка на номер телефона
    public static boolean isPhoneNumber(String data) {
        if (data.charAt(0) == ' ') {
            data = data.replace(' ', '+');
        }
        String phoneNumberRegex = "^(\\+7|7|8)\\d{10}$";
        System.out.println("Entered data: " + data);
        return data.matches(phoneNumberRegex);
    }

    // Проверка на адрес электронной почты
    public static boolean isEmail(String data) {
        // Пример для проверки адреса электронной почты: example@mail.com
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return data.matches(emailRegex);
    }

    // Проверка на никнейм в Telegram
    public static boolean isTelegramUsername(String data) {
        // Пример для проверки никнейма в Telegram: @username
        String telegramUsernameRegex = "^@([A-Za-z0-9_]{5,32})$";
        return data.matches(telegramUsernameRegex);
    }

    public Customer findByEmail(String email) {
        return customerRepository.findByContactEmail(email).get();
    }

    public void addProblem(String customerId, String problemId) {
        Customer customer = customerRepository.findById(customerId).get();
        customer.addProblem(problemId);
        customerRepository.save(customer);
    }
    public List<String> getAllCustomersProblemIds(String customerId) {
        Customer customer = customerRepository.findById(customerId).get();
        return customer.getProblemsId();
    }
    public List<Problem> getAllCustomersProblems(String customerId) {
        Customer customer = customerRepository.findById(customerId).get();
        List<Problem> problems = problemService.getAllCustomersProblems(customer.getProblemsId());
        return new ArrayList<>();
    }

    public Customer findByProblemId(String problemId) {
        System.out.println("problemId=" + problemId);
        Customer customer = customerRepository.findByProblemsIdContaining(problemId).get();
        System.out.println("Found the customer " + customer.getName() + " with problemId " + problemId);

        return customer;
    }

    public void changeCustomerStatusOnCustomer(String problemId) {
        Customer customer = customerRepository.findByProblemsIdContaining(problemId).get();
        customer.setStatus(Status.CUSTOMER);
        Customer result = customerRepository.save(customer);
        System.out.println("Customer's status: " + result.getStatus());
    }

    public List<Customer> getAllCustomersWithStatusCustomer() {
        List<Customer> customers = customerRepository.findAll();
        List<Customer> customersWithStatusCustomer = new ArrayList<>();
        for (int i = 0; i < customers.size(); i++) {
            System.out.println("customer's email: " + customers.get(i).getContact().getEmail());
            if (customers.get(i).getStatus() == Status.CUSTOMER)
            {
                System.out.println("adding Client to the list");
                customersWithStatusCustomer.add(customers.get(i));
            }
        }
        return customers;
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }
}
