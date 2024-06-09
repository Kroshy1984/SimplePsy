package ru.sfedu.simplepsyspecialist.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Document("customer")
public class Customer {

    @Id
    private String id;

    private String name;
    private String surname;
    private Status status;
    private Contact contact;
    private String description;
    private LocalDate dateOfFirstCall;
    private byte[] avatar;
    private List<String> problemsId;
   // private LocalDate dateOfRegistration = LocalDate.of(1000, 1 , 1);
    public Customer() {
    }

    public Customer(String name, Status status, Contact contact) {
        this.name = name;
        this.status = status;
        this.contact = contact;
    }

    public Customer(String name, Status status, Contact contact, String dateOfFirstCall) {
        this.name = name;
        this.status = status;
        this.contact = contact;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.dateOfFirstCall = LocalDate.parse(dateOfFirstCall, formatter);
    }
    public Customer(String name, Status status, Contact contact, String dateOfFirstCall, MultipartFile avatar) throws IOException {
        this.name = name;
        this.status = status;
        this.contact = contact;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.dateOfFirstCall = LocalDate.parse(dateOfFirstCall, formatter);
        this.avatar = avatar.getBytes();
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getDateOfFirstCall() {
        return dateOfFirstCall.toString();
    }

    public void setDateOfFirstCall(String dateOfFirstCall) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.dateOfFirstCall = LocalDate.parse(dateOfFirstCall, formatter);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(MultipartFile avatar) throws IOException {
        this.avatar = avatar.getBytes();
    }
    public void setByteAvatar(byte[] avatar)
    {
        this.avatar = avatar;
    }

    public List<String> getProblemsId() {
        return problemsId;
    }

    public void setProblemsId(List<String> problemsId) {
        this.problemsId = problemsId;
    }

    public void addProblem(String problemId) {
        if (problemsId == null)
        {
            problemsId = new ArrayList<>();
        }
        problemsId.add(problemId);
    }
    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", contact=" + contact +
                '}';
    }


}
