package ru.sfedu.simplepsy.customer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/SimplePsy/V1/customer")
public class CustomerController {

    @PostMapping("/new")
    public ResponseEntity<String> createResource(@RequestBody String requestPayload) {
        // Логика для обработки POST запроса
        return new ResponseEntity<>("Resource created", HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<String> getResource() {
        // Логика для обработки GET запроса
        return new ResponseEntity<>("Resource retrieved", HttpStatus.OK);
    }

//    @PutMapping("/endpoint")
//    public ResponseEntity<String> updateResource(@RequestBody String requestPayload) {
//        // Логика для обработки PUT запроса
//        return new ResponseEntity<>("Resource updated", HttpStatus.OK);
//    }
//
//    @PatchMapping("/endpoint")
//    public ResponseEntity<String> partialUpdateResource(@RequestBody String requestPayload) {
//        // Логика для обработки PATCH запроса
//        return new ResponseEntity<>("Resource partially updated", HttpStatus.OK);
//    }
//
//    @DeleteMapping("/endpoint")
//    public ResponseEntity<String> deleteResource() {
//        // Логика для обработки DELETE запроса
//        return new ResponseEntity<>("Resource deleted", HttpStatus.OK);
//    }
}