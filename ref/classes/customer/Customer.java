package ru.sfedu.simplepsy.classes.customer;

import ru.sfedu.simplepsy.classes.Contact;
import ru.sfedu.simplepsy.classes.Problem;
import ru.sfedu.simplepsy.classes.types.StatusType;

import java.util.ArrayList;

public class Customer {

    private String id; // идентификатор
    private StatusType statusType; // статус
    private String name; // имя
    private Contact contact; // контакт

    public Customer(String name, String id, StatusType statusType, Contact contact) {
        this.name = name;
        this.id = id;
        this.statusType = statusType;
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public StatusType getStatusType() {
        return statusType;
    }

    public void setStatusType(StatusType statusType) {
        this.statusType = statusType;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}