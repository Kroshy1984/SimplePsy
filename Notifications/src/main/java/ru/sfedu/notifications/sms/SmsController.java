package ru.sfedu.notifications.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/sms")
public class SmsController {

    private SmsService smsService;
    @Autowired
    public SmsController(SmsService smsService)
    {
        this.smsService = smsService;
    }
    @PostMapping("/text")
    public ResponseEntity<String> sendSms(@RequestParam("to") String to, @RequestParam("text") String text) {
        System.out.println("Got the message " + text);
        System.out.println("The text is: " + text);
        smsService.sendSms(to, text);
        return ResponseEntity.ok("Success");
    }
}
