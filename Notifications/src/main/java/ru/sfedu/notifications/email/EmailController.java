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

    @PostMapping("/scoring-invitation")
    public ResponseEntity<String> sendScoringInvitationEmail(@RequestBody List<String> array) {
        System.out.printf("got email %s and name %s\n", array.get(2), array.get(1));
        emailService.sendScoringInvitationEmail(array);
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/scoring-result")
    public ResponseEntity<String> sendScoringResultEmail(@RequestParam("email") String email,
                                                @RequestParam("specialistName") String specialistName,
                                                @RequestParam("customerName") String customerName,
                                                @RequestParam("problemId") String problemId) {
        System.out.printf("got email " + email + "and name " + customerName);
        emailService.sendScoringResultEmail(email, specialistName, customerName, problemId);
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/changePass")
    public ResponseEntity<String> sendChangePasswordPage(@RequestParam("email") String email,
                                                         @RequestParam("specialistId") String specialistId)
    {
        System.out.println("In method sendChangePasswordPage got the email " + email);
        emailService.sendChangePasswordPage(email, specialistId);
        return ResponseEntity.ok("mail to the " + email + "successfully sent");
    }
//    @PostMapping("/html")
//    fun sendHtmlEmail(@RequestBody request: MultipleReceiverRequest) {
//        emailService.sendHtmlEmail(request)
//    }
}
