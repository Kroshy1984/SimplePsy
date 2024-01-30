package ru.sfedu.session;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Session")
public class Session {

    @Id
    private String id;

    @NotBlank
    @Pattern(regexp = "^[а-яА-ЯёЁ]+\\s[0-9]{2}/[0-9]{2}/[0-9]{4}$")
    private String date;

    @NotBlank
    @Pattern(regexp = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$")
    private String time;

    @NotBlank
    @Size(min = 1, max = 255)
    private String typeOfClients;
    private String timeOfMeeting;
    @Pattern(regexp = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$")
    private String clientId;

    public Session() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTypeOfClients() {
        return typeOfClients;
    }

    public void setTypeOfClients(String typeOfClients) {
        this.typeOfClients = typeOfClients;
    }

    public String getTimeOfMeeting() {
        return timeOfMeeting;
    }

    public void setTimeOfMeeting(String timeOfMeeting) {
        this.timeOfMeeting = timeOfMeeting;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Session(String client_id, String date) {
        this.date = date;
        this.clientId = client_id;
    }
}
