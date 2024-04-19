package ru.sfedu.notifications.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/emails")
public class EmailController {
    private EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/text")
    public ResponseEntity<String> sendTextEmail(@RequestParam("email") String email,
                                                @RequestParam("name") String name) {
        System.out.printf("got email %s and name %s\n", email, name);
        emailService.sendTextEmail(email, name);
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/scoring")
    public ResponseEntity<String> sendScoringEmail(@RequestBody List<String> array) {
        System.out.printf("got email %s and name %s\n", array.get(2), array.get(1));
        emailService.sendScoringEmail(array);
        return ResponseEntity.ok("Success");
    }

//    @PostMapping("/html")
//    fun sendHtmlEmail(@RequestBody request: MultipleReceiverRequest) {
//        emailService.sendHtmlEmail(request)
//    }
}
