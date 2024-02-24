package ru.sfedu.notifications.telegram.service;


import com.google.gson.JsonArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.sfedu.notifications.telegram.config.BotConfig;
import ru.sfedu.notifications.telegram.model.Spec;
import ru.sfedu.notifications.telegram.model.SpecRepository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


@Component
public class TelegramBot extends TelegramLongPollingBot {

    private SpecRepository specRepository;
    private SpecService specService;

    final BotConfig config;
    static final String HELP_TEXT = "demo spring telegram bot \n Choose the command from the menu or type it\n" +
            "Type /start to see welcum message\n" +
            "Type /mydata to get stored data \n" +
            "Type /deletedata to delete data";
    static final String ERROR_TEXT = "Error occurred: ";
    static final String YES_BUTTON = "YES_BUTTON";
    static final String NO_BUTTON = "NO_BUTTON";
    final String emailPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    Pattern pattern = Pattern.compile(emailPattern);
    @Autowired
    public TelegramBot(BotConfig config, SpecRepository specRepository, SpecService specService) {
        this.config = config;
        this.specRepository = specRepository;
        this.specService = specService;
        List<BotCommand> listofCommands = new ArrayList<>();
        listofCommands.add(new BotCommand("/start", "get a welcome message"));
        listofCommands.add(new BotCommand("/mydata", "get your data stored"));
        listofCommands.add(new BotCommand("/deletedata", "delete my data"));
        listofCommands.add(new BotCommand("/help", "info how to use this bot"));
        listofCommands.add(new BotCommand("/settings", "set your preferences"));
        listofCommands.add(new BotCommand("/register", "sign up"));
        try {
            this.execute(new SetMyCommands(listofCommands, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            System.out.println("Error setting bot's command list: " + e.getMessage());
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
                sendMessage(chatId, "Это почта");
                JsonArray sessions = specService.sendRequestToGetSessions(messageText);
                System.out.println(sessions);
//                StringBuilder listOfDates = new StringBuilder();
//                for (Object obj : sessions) {
//                    if (obj instanceof Map) {
//                        Map<String, Object> map = (Map<String, Object>) obj;
//                        String date = map.get("date").toString();
//                        listOfDates.append(date + "\n");
//                        System.out.println("Date: " + date);
//                    }
//
//                }
               // sendMessage(chatId, listOfDates.toString());
            }
            else {
                switch (messageText) {
                    case "/start":
                        sendMessage(chatId, "Здесь вы можете получить список ваших сессий" +
                                "\nПожалуйста, введите вашу почту");
//                        registerClient(update.getMessage());
//                        startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                        break;
                    default:
                        sendMessage(chatId, "Команда не найдена");
                }
            }
        }
        else if(update.hasCallbackQuery())
        {
            String callBackData = update.getCallbackQuery().getData();
            long messageId = update.getCallbackQuery().getMessage().getMessageId();
            long chatId = update.getCallbackQuery().getMessage().getChatId();
            String text = callBackData.equals(YES_BUTTON) ? "Pressed YES button" : "Pressed NO button";
            executeEditMessageText(text, chatId, messageId);
        }
    }

    private void register(long chatId)
    {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Do you want to sign up");

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        InlineKeyboardButton yesButton = new InlineKeyboardButton();

        yesButton.setText("Yes");
        yesButton.setCallbackData(YES_BUTTON);

        InlineKeyboardButton noButton = new InlineKeyboardButton();
        noButton.setText("No");
        noButton.setCallbackData(NO_BUTTON);

        rowInline.add(yesButton);
        rowInline.add(noButton);
        rowsInline.add(rowInline);

        markupInline.setKeyboard(rowsInline);
        message.setReplyMarkup(markupInline);
        executeMessage(message);

    }
    private void executeMessage(SendMessage message){
        try {
            execute(message);
        } catch (TelegramApiException e) {
            System.out.println(ERROR_TEXT + e.getMessage());
        }
    }
    private void registerClient(Message message) {
        if(specRepository.findById(message.getChatId()).isEmpty())
        {
            long chatId = message.getChatId();
            Chat chat = message.getChat();

            Spec client = new Spec();
            client.setChatId(chatId);
            client.setFirstName(chat.getFirstName());
            client.setLastName(chat.getLastName());
            client.setUserName(chat.getUserName());
            client.setRegisteredAt(new Timestamp(System.currentTimeMillis()));

            specRepository.save(client);

            System.out.println("Client saved: " + client);
        }
    }

    private void startCommandReceived(Long chatId, String name) {
        String answer = ("Greetings, " + name);
        System.out.println("Replied to Client: " + name);
        sendMessage(chatId, answer);
    }

    private void sendMessage(long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("weather");
        row.add("random phrase");
        keyboardRows.add(row);

        row = new KeyboardRow();
        row.add("register");
        row.add("check my data");
        row.add("delete my data");
        keyboardRows.add(row);

        keyboardMarkup.setKeyboard(keyboardRows);
        message.setReplyMarkup(keyboardMarkup);


        try {
            execute(message);
        } catch (TelegramApiException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }
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

    private void executeErrorMessage(SendMessage message)
    {
        try {
            execute(message);
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
