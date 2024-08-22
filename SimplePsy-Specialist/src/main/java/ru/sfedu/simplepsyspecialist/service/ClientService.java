package ru.sfedu.simplepsyspecialist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sfedu.simplepsyspecialist.entity.Client;
import ru.sfedu.simplepsyspecialist.entity.Customer;
import ru.sfedu.simplepsyspecialist.entity.nested.Status;
import ru.sfedu.simplepsyspecialist.repo.ClientRepository;

import java.util.List;

@Service
public class ClientService {
    ClientRepository clientRepository;
    CustomerService customerService;
    ProblemService problemService;

    @Autowired
    public ClientService(ClientRepository clientRepository, CustomerService customerService, ProblemService problemService) {
        this.clientRepository = clientRepository;
        this.customerService = customerService;
        this.problemService = problemService;
    }

    public Client save(Client client) {
        System.out.println("saving client");
        return clientRepository.save(client);
    }

    public Client findByEmail(String email) {
        return clientRepository.findByContactEmail(email);
    }

    public Client findById(String clientId) {
        Client client = clientRepository.findById(clientId).get();
        System.out.println("found the ClientDTO name: " + client.getName());
        return client;
    }

    public Client getClientByCustomerId(String customerId) {
        Customer customer = customerService.findById(customerId);
        // TODO mapper customer to client
        customerService.findById(customerId);
        return new Client();
    }

    public Client getCustomerByProblemId(String problemId) {
        System.out.println("in method getClientByProblemId got problemId: " + problemId);
        //mapper customer to client
        return new Client();
    }

    public void changeCustomerStatusOnCustomer(String problemId) {
        Customer customer = customerService.findByProblemId(problemId);
        customer.setStatus(Status.CUSTOMER);
    }

    public List<Client> findAll() {
        List<Client> clients = clientRepository.findAll();
        for (Client c : clients) {
            System.out.println(c.getId());
        }
        return clients;
    }
}
