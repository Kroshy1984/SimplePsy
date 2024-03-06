package ru.sfedu.simplepsyspecialist.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("Session")
public class Session {

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
    private List<Client> clients;

    public Session() {
    }

    public Session(String date, String time, String typeOfClients, String timeOfMeeting, List<Client> clients) {
        this.date = date;
        this.time = time;
        this.typeOfClients = typeOfClients;
        this.timeOfMeeting = timeOfMeeting;
        this.clients = clients;
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

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
}
