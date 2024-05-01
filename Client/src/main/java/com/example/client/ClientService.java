package com.example.client;

import com.example.client.dto.ClientDTO;
import com.example.client.dto.ClientMapper;
import com.example.client.dto.CustomerDTO;
import com.example.client.dto.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ClientService {
    ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public Client findByEmail(String email) {
        return clientRepository.findByContactEmail(email);
    }

    public ClientDTO findById(String clientId) {
        Client client = clientRepository.findById(clientId).get();
        ClientDTO clientDTO = ClientMapper.INSTANCE.clinetToClientDTO(client);
        System.out.println("found the ClientDTO name: " + clientDTO.getName());
        return clientDTO;
    }

    public Client getClientByCustomerId(String customerId) {
        System.out.println("in method getClientByCustomerId");
        String baseUrl = System.getenv().getOrDefault("CUSTOMER_SERVICE_URL", "http://localhost:8080");
        String url = "/SimplePsy/V1/customer/customerToClient";
        WebClient webClient = WebClient.builder().baseUrl(baseUrl).build();

        ResponseEntity<CustomerDTO> result = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(url)
                        .queryParam("customerId", customerId)
                        .build())
                .retrieve()
                .toEntity(CustomerDTO.class)
                .block();
        System.out.println("Customer name: " + result.getBody().getName());
        Client client = CustomerMapper.INSTANCE.customerDTOToClient(result.getBody());
        System.out.println("CustomerDTO mapped to the client object with name: " + client.getName());
        System.out.println("CustomerDTO mapped to the client object with surname: " + client.getSurname());
        System.out.println("CustomerDTO mapped to the client object with email: " + client.getContact().getEmail());
        System.out.println("CustomerDTO mapped to the client object with tg: " + client.getContact().getTg());
        return client;
    }

    public Client getCustomerByProblemId(String problemId) {
        System.out.println("in method getClientByProblemId got problemId: " + problemId);
        String baseUrl = System.getenv().getOrDefault("CUSTOMER_SERVICE_URL", "http://localhost:8080");
        String url = "/SimplePsy/V1/customer/getCustomerByProblemId";
        WebClient webClient = WebClient.builder().baseUrl(baseUrl).build();

        ResponseEntity<CustomerDTO> result = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(url)
                        .queryParam("problemId", problemId)
                        .build())
                .retrieve()
                .toEntity(CustomerDTO.class)
                .block();
        System.out.println("Customer name: " + result.getBody().getName());
        Client client = CustomerMapper.INSTANCE.customerDTOToClient(result.getBody());
        System.out.println("CustomerDTO mapped to the client object with name: " + client.getName());
        System.out.println("CustomerDTO mapped to the client object with surname: " + client.getSurname());
        System.out.println("CustomerDTO mapped to the client object with email: " + client.getContact().getEmail());
        System.out.println("CustomerDTO mapped to the client object with tg: " + client.getContact().getTg());
        return client;
    }
}
