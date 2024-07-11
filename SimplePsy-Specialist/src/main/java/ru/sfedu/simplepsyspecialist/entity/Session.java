package ru.sfedu.simplepsyspecialist.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.sfedu.simplepsyspecialist.entity.nested.PaymentType;
import ru.sfedu.simplepsyspecialist.entity.nested.SessionType;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Document("Session")
public class Session {

    @Id
    private String id;

    private String problem;

    @Pattern(regexp = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$")
    private String specialistId;

    @Pattern(regexp = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$")
    private String clientId;

    private Client client;
    private SessionType sessionType;
    @NotBlank
    private LocalDateTime date;
    private String place;
    private LocalTime timeStart;
    private LocalTime timeFinish;
    private PaymentType paymentType;
    private boolean isRepeatable;
    private String timesToRepeat;
    private boolean isNotifiable;
    private String notificationTime;


    public Session() {
    }

    public Session(LocalDateTime date, String problem, String specialistId, String clientId) {
        this.date = date;
        this.problem = problem;
        this.specialistId = specialistId;
        this.clientId = clientId;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getSpecialistId() {
        return specialistId;
    }

    public void setSpecialistId(String specialistId) {
        this.specialistId = specialistId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public SessionType getSessionType() {
        return sessionType;
    }

    public void setSessionType(SessionType sessionType) {
        this.sessionType = sessionType;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public LocalTime getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(LocalTime timeStart) {
        this.timeStart = timeStart;
    }

    public LocalTime getTimeFinish() {
        return timeFinish;
    }

    public void setTimeFinish(LocalTime timeFinish) {
        this.timeFinish = timeFinish;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public boolean isRepeatable() {
        return isRepeatable;
    }

    public void setRepeatable(boolean repeatable) {
        isRepeatable = repeatable;
    }

    public String getTimesToRepeat() {
        return timesToRepeat;
    }

    public void setTimesToRepeat(String timesToRepeat) {
        this.timesToRepeat = timesToRepeat;
    }

    public boolean isNotifiable() {
        return isNotifiable;
    }

    public void setNotifiable(boolean notifiable) {
        isNotifiable = notifiable;
    }

    public String getNotificationTime() {
        return notificationTime;
    }

    public void setNotificationTime(String notificationTime) {
        this.notificationTime = notificationTime;
    }
}
