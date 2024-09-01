package ru.sfedu.simplepsyspecialist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sfedu.simplepsyspecialist.entity.Client;
import ru.sfedu.simplepsyspecialist.entity.Specialist;
import ru.sfedu.simplepsyspecialist.service.ClientService;
import ru.sfedu.simplepsyspecialist.service.SpecialistService;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/SimplePsy/V1/client")
public class ClientController {
    ClientService clientService;
    SpecialistService specialistService;

    @Autowired
    public ClientController(ClientService clientService, SpecialistService specialistService) {
        this.clientService = clientService;
        this.specialistService = specialistService;
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
        System.out.println("received client's email " + email);
        Client client = clientService.findByEmail(email);
        if(client == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Client with email " + email + " not found");
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body(client.getId());
        }
    }
    @PostMapping("/new")
    public ResponseEntity<String> newClient(@RequestBody Client client)
    {
        clientService.save(client);
        return ResponseEntity.ok("Client successfully created");
    }
    @GetMapping("/findById")
    public ResponseEntity<Client> findById(@RequestParam("clientId") String clientId)
    {
        System.out.println("got the clientId: " + clientId);
        Client clientDTO = clientService.findById(clientId);
        return ResponseEntity.ok(clientDTO);
    }

    //запрос приходит из скоринга после прохождения анкеты
    @PostMapping("/newClient/{problemId}")
    public ResponseEntity<String> newClientFromCustomer(@PathVariable String problemId)
    {
        System.out.println("in method newClientFromCustomer got problemId: " + problemId);
        Client client = clientService.getCustomerByProblemId(problemId);
        clientService.changeCustomerStatusOnCustomer(problemId);
        System.out.println("got the client with id: " + client.getId());
        Client savedClient = clientService.save(client);
        return ResponseEntity.ok("client successfully created");
    }
    @GetMapping("/findAll")
    public ResponseEntity<List<Client>> findAllClients()
    {
        return ResponseEntity.ok(clientService.findAll());
    }

    @GetMapping("/findAll/{specialistId}")
    public ResponseEntity<List<Client>> findAllCustomer(@PathVariable String specialistId)
    {
        System.out.println("currentSpecialistId: " + specialistId);
        Specialist specialist = specialistService.findById(specialistId);
        List<String> customerIds = specialist.getCustomerIds();
        List<Client> customers = new ArrayList<>();
        for (String customerId : customerIds) {
            Client client = clientService.findById(customerId);
            if (client == null) {
                continue;
            } else {
                customers.add(clientService.findById(customerId));
            }
        }
        System.out.println("Customers list:");
        customers.stream().forEach(customer -> System.out.println(customer.getTypeOfClient()));
        return ResponseEntity.ok(customers);
    }
}
