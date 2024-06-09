package ru.sfedu.simplepsyspecialist.notification.telegram.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.sfedu.simplepsyspecialist.notification.telegram.config.BotConfig;
import ru.sfedu.simplepsyspecialist.notification.telegram.model.Spec;
import ru.sfedu.simplepsyspecialist.notification.telegram.model.SpecRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


@Component
public class TelegramBot extends TelegramLongPollingBot {

    private SpecRepository specRepository;
    private SpecService specService;

    final BotConfig config;
    final String emailPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    Pattern pattern = Pattern.compile(emailPattern);
    @Autowired
    public TelegramBot(BotConfig config, SpecRepository specRepository, SpecService specService) {
        this.config = config;
        this.specRepository = specRepository;
        this.specService = specService;
        List<BotCommand> listofCommands = new ArrayList<>();
        listofCommands.add(new BotCommand("/start", "get a welcome message"));
        try {
            this.execute(new SetMyCommands(listofCommands, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();
            if (messageText.contains("/send") && config.getOwnerId().longValue() == chatId.longValue()) {
                List<Spec> specs = specRepository.findAll();
            }
            else if(pattern.matcher(messageText).matches())
            {
                // Форматирование даты и времени в требуемый формат
                DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-dd-MM HH:mm");

                List<LocalDateTime> sessions = specService.sendRequestToGetSessions(messageText);
                StringBuilder stringBuilder = new StringBuilder("список ваших сессий:\n");
                for (int i = 0; i < sessions.size(); i++) {
                    String output = sessions.get(i).format(outputFormatter);
                    stringBuilder.append(output + "\n");
                }
                sendMessage(chatId, stringBuilder.toString());
            }
            else {
                switch (messageText) {
                    case "/start":
                        sendMessage(chatId, "Здесь вы можете получить список ваших сессий" +
                                "\nПожалуйста, введите вашу почту");
                        break;
                    default:
                        sendMessage(chatId, "Команда не найдена");
                }
            }
        }
    }
//    private void registerClient(Message message) {
//        if(specRepository.findById(message.getChatId()).isEmpty())
//        {
//            long chatId = message.getChatId();
//            Chat chat = message.getChat();
//
//            Spec client = new Spec();
//            client.setChatId(chatId);
//            client.setFirstName(chat.getFirstName());
//            client.setLastName(chat.getLastName());
//            client.setUserName(chat.getUserName());
//            client.setRegisteredAt(new Timestamp(System.currentTimeMillis()));
//
//            specRepository.save(client);
//
//            System.out.println("Client saved: " + client);
//        }
//    }

    //основной метод для отправки сообщений
    private void sendMessage(long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }

    //метод для обработки редактирования сообщений,
    // то есть когда пользователь жмет и сообщение и выбирает edit
    private void executeEditMessageText(String text, long chatId, long messageId)
    {
        EditMessageText messageText = new EditMessageText();
        messageText.setChatId(String.valueOf(chatId));
        messageText.setText(text);
        messageText.setMessageId((int) messageId);
        try {
            execute(messageText);
        } catch (TelegramApiException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }

//    @Scheduled(cron = "${cron.scheduler}")
//    private void sendAds()
//    {
//
//        List<Client> Clients = ClientRepository.findAll();
//
//        for (Ads a : list) {
//            for (Client Client : Clients) {
//                SendMessage sendMessage = new SendMessage();
//                sendMessage.setChatId(String.valueOf(Client.getChatId()));
//                sendMessage.setText(a.getAd());
//                executeMessage(sendMessage);
//            }
//        }
//    }
}
