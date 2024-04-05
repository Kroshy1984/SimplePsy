package ru.sfedu.simplepsyspecialist.dto;

import java.time.LocalDate;

public class ClientDTO {

    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private String problemId;
    private TypeOfClients typeOfClients;
    private Contact contact;

    public ClientDTO() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getProblemId() {
        return problemId;
    }

    public void setProblemId(String problemId) {
        this.problemId = problemId;
    }

    public TypeOfClients getTypeOfClients() {
        return typeOfClients;
    }

    public void setTypeOfClients(String TypeOfClients) {
        this.typeOfClients = typeOfClients;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
