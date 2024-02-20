package ru.sfedu.notifications.telegram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Service
public class TelegramNotificationService {

    @Value("${bot.name}")
    private String botUsername;

    @Value("${bot.token}")
    private String botToken;
    private TelegramBot telegramBot;

    @Autowired
    public TelegramNotificationService(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    public void sendTelegramNotification(Long chatId, String message) {
        SendMessage sendMessage = new SendMessage();
       // sendMessage.setChatId(getChatIdByUsername(username));
        sendMessage.setText(message);
        telegramBot.sendMessageToUser(chatId, message);
    }
}
