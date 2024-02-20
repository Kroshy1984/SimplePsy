package ru.sfedu.notifications.telegram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TelegramBotController {

    private TelegramNotificationService telegramNotificationService;

    @Autowired
    public TelegramBotController(TelegramNotificationService telegramNotificationService) {
        this.telegramNotificationService = telegramNotificationService;
    }

    @PostMapping("/send-message")
    public void sendMessageToTelegramBot(@RequestParam("username") String username)
    {

        //telegramNotificationService.sendTelegramNotification(messageDto.getUsername(), messageDto.getMessage());
    }
}
