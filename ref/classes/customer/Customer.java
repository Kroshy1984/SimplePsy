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
    private String comment; // комментарий
    private ArrayList<Problem> problems = new ArrayList<>(); // проблемы

    public Customer(String id, String name, StatusType statusType, Contact contact) {
        this.id = id;
        this.name = name;
        this.statusType = statusType;
        this.contact = contact;
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