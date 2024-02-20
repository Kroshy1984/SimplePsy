package com.example.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/SimplePsyClient/V1/client")
public class ClientController {
    ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/new")
    public ResponseEntity<String> createClient(@RequestBody Object object) {
        Client client = (Client) object;
        clientService.save(client);
        return ResponseEntity.ok("Successfully created or updated client");
    }
}
