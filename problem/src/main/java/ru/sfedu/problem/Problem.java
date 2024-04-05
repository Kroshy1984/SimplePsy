package ru.sfedu.problem;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "problems")
public class Problem {

    @Id
    private String id;
    private Status status;

    private String descriptionOfProblem;
    private LocalDateTime dateOfFirstContact;

    private String clientId;

    public Problem(Status status, String descriptionOfProblem, LocalDateTime dateOfFirstContact) {
        this.status = status;
        this.descriptionOfProblem = descriptionOfProblem;
        this.dateOfFirstContact = dateOfFirstContact;
    }

    public Problem() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDescriptionOfProblem() {
        return descriptionOfProblem;
    }

    public void setDescriptionOfProblem(String descriptionOfProblem) {
        this.descriptionOfProblem = descriptionOfProblem;
    }

    public LocalDateTime getDateOfFirstContact() {
        return dateOfFirstContact;
    }

    public void setDateOfFirstContact(LocalDateTime dateOfFirstContact) {
        this.dateOfFirstContact = dateOfFirstContact;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
