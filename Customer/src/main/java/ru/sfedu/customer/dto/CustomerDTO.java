package ru.sfedu.customer.dto;


import ru.sfedu.customer.Contact;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomerDTO {

    private String id;
    private String name;
    private String surname;
    private Contact contact;
    private LocalDate dateOfBirth;
    private List<String> problemsId;

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

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public List<String> getProblemsId() {
        return problemsId;
    }
    public void addProblem(String problemId) {
        if (problemsId == null)
        {
            problemsId = new ArrayList<>();
        }
        problemsId.add(problemId);
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
