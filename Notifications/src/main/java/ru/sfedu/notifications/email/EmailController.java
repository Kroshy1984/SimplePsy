package ru.sfedu.notifications.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        System.out.println(String.format("got email %s and name", email, name));
        emailService.sendTextEmail(email, name);
        return ResponseEntity.ok("Success");
    }


//    @PostMapping("/html")
//    fun sendHtmlEmail(@RequestBody request: MultipleReceiverRequest) {
//        emailService.sendHtmlEmail(request)
//    }
}
