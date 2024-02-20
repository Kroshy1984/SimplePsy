package com.example.client;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Client")
public class Client {
    @Id
    String id;
    String name;
    String surname;
    MaritalStatus maritalStatus;

    TypeOfClient typeOfClient;

    public Client() {
    }

    public Client(String id, String name, String surname, MaritalStatus maritalStatus, TypeOfClient typeOfClient) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.maritalStatus = maritalStatus;
        this.typeOfClient = typeOfClient;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public TypeOfClient getTypeOfClient() {
        return typeOfClient;
    }

    public void setTypeOfClient(TypeOfClient typeOfClient) {
        this.typeOfClient = typeOfClient;
    }
}
