package com.example.client;

import com.example.client.dto.ClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/SimplePsyClient/V1/client")
public class ClientController {
    ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

//    @ResponseStatus(HttpStatus.OK)
//    @PostMapping("/new")
//    public ResponseEntity<String> createClient(@RequestBody List<String> object) {
//        System.out.println("Got the client params: ");
//        object.stream().forEach(System.out::println);
//        System.out.println("creating new client");
//        Client client = new Client();
//        client.setName(object.get(0));
//        client.setSurname(object.get(1));
//        //client.setAge(Integer.parseInt(object.get(2)));
//        client.contact.setPhone(object.get(3));
//        client.contact.setEmail(object.get(4));
//        System.out.println(client.toString());
//        Client result = clientService.save(client);
//        System.out.println(result.toString());
//        return ResponseEntity.ok("Successfully created or updated client");
//    }

    @GetMapping("/findByEmail")
    public ResponseEntity<String> findClientByEmail(@RequestParam("clientEmail") String email)
    {
        System.out.println("received client's email");
        Client client = clientService.findByEmail(email);
        if(client == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Client with email " + email + " not found");
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body(client.id);
        }
    }
    @PostMapping("/new")
    public ResponseEntity<String> newClient(@RequestBody Client client)
    {
        clientService.save(client);
        return ResponseEntity.ok("Client successfully created");
    }
    @GetMapping("/findById")
    public ResponseEntity<ClientDTO> findById(@RequestParam("clientId") String clientId)
    {
        System.out.println("got the clientId: " + clientId);
        ClientDTO clientDTO = clientService.findById(clientId);
        return ResponseEntity.ok(clientDTO);
    }

    //запрос приходит из скоринга после прохождения анкеты
    @PostMapping("/newClient/{customerId}")
    public ResponseEntity<String> newClientFromCustomer(@PathVariable String customerId)
    {
        System.out.println("in method newClientFromCustomer");
        Client client = clientService.getClientByCustomerId(customerId);
        Client savedClient = clientService.save(client);
        return ResponseEntity.ok("client successfully created");
    }
}
