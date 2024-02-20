package ru.sfedu.notifications.sms;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class SmsService {

    public void sendSms(String to, String text)
    {
        System.out.println(String.format("sending message %s to %s", text, to));
        String url = "https://mainsms.ru/api/message/send";
        String project = "jugTest";
        String apiKey = "809863fc3f8274ce1bd885bdfa596f2e";
        WebClient webClient = WebClient.builder().baseUrl(url).build();
        MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();
        formData.add("project", project);
        formData.add("recipients", to);
        formData.add("message", text);
        formData.add("apikey", apiKey);

        // Выполнение запроса
        webClient.post()
                .uri("https://mainsms.ru/api/message/send")
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromMultipartData(formData))
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(response -> {
                    // Обработка ответа
                    System.out.println("Response: " + response);
                });
    }
}
