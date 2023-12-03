package ru.sfedu.simplepsy.classes.customer;

import ru.sfedu.simplepsy.classes.Contact;
import ru.sfedu.simplepsy.classes.Problem;
import ru.sfedu.simplepsy.classes.types.StatusType;

import java.util.ArrayList;
import java.util.UUID;

public class Customer {

    private UUID id; // идентификатор
    private StatusType statusType; // статус
    private String name; // имя
    private Contact contact; // контакт
    private String comment; // комментарий
    private ArrayList<Problem> problems = new ArrayList<>(); // проблемы

    public Customer(UUID id, StatusType statusType, String name, Contact contact) {
        this.id = id;
        this.statusType = statusType;
        this.name = name;
        this.contact = contact;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public StatusType getStatusType() {
        return statusType;
    }

    public void setStatusType(StatusType statusType) {
        this.statusType = statusType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public ArrayList<Problem> getProblems() {
        return problems;
    }

    public void setProblems(ArrayList<Problem> problems) {
        this.problems = problems;
    }
}