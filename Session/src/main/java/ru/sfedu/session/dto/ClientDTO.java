package ru.sfedu.session.dto;

public class ClientDTO {
    String name;
    String surname;
    TypeOfClients typeOfClients;
    Contact contact;

    public ClientDTO() {
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

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public TypeOfClients getTypeOfClients() {
        return typeOfClients;
    }

    public void setTypeOfClients(TypeOfClients typeOfClients) {
        this.typeOfClients = typeOfClients;
    }
}
