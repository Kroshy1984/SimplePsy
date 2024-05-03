package ru.sfedu.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import ru.sfedu.customer.dto.CustomerDTO;
import ru.sfedu.customer.dto.CustomerMapper;
import ru.sfedu.customer.dto.CustomersSearch;
import ru.sfedu.customer.dto.ProblemDTO;
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
        if (customers.size() == 0 || customers.isEmpty())
            return new ArrayList<>();

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
        customer.setStatus(Status.LEAD);
        System.out.println("Customer CustomerDTO" + customer.getProblemsId());
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
        Customer oldCustomer = customerRepository.findById(id).get();
        customer.setProblemsId(oldCustomer.getProblemsId());
        customer.setStatus(oldCustomer.getStatus());
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
        System.out.println("customer's status " + foundCustomer.getName() + " was updated to CUSTOMER");
        customerRepository.save(foundCustomer);
    }

    public boolean findByContactData(String data)
    {
        if (isPhoneNumber(data)) {
            if (data.charAt(0) == ' ') {
                data = data.replace(' ', '+');
            }
            return customerRepository.existsByContactPhone(data);
        } else if (isEmail(data)) {
            return  customerRepository.existsByContactEmail(data);
        } else if (isTelegramUsername(data)) {
            return customerRepository.existsByContactTg(data);
        } else {
            System.out.println(data + " - не является ни номером телефона, ни адресом электронной почты, ни никнеймом в Telegram.");
            return false;
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
    public List<ProblemDTO> getAllCustomersProblems(String customerId) {
        List<String> problems = getAllCustomersProblemIds(customerId);
        System.out.println("Got the customer's problem. The id of first one is: " + problems.get(0));
        String baseUrl = System.getenv().getOrDefault("PROBLEM_SERVICE_URL", "http://localhost:8087");
        String url = "/SimplePsyProblem/V1/problem/customer/problems";
        WebClient webClient = WebClient.builder().baseUrl(baseUrl).build();
        ResponseEntity<List<ProblemDTO>> response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(url)
                        .queryParam("problemsIds", problems)
                        .build())
                .retrieve()
                .toEntityList(ProblemDTO.class)
                .block();
        System.out.println("In method getAllCustomersProblems the result of the first one: " + response.getBody().get(0));
        return response.getBody();
    }

    public CustomerDTO findByProblemId(String problemId) {
        Customer customer = customerRepository.findByProblemsIdContaining(problemId).get();
        System.out.println("Found the customer " + customer.getName() + " with problemId " + problemId);
        CustomerDTO customerDTO = CustomerMapper.INSTANCE.customerToCustomerDTO(customer);
        return customerDTO;
    }
}
