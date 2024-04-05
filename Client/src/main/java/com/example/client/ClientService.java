package com.example.client;

import com.example.client.dto.ClientDTO;
import com.example.client.dto.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
