package ru.sfedu.simplepsycustomer.simplepsy.customer;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Builder
@Document("customer")
public class Customer {

    @Id
    private String id;

    private String name;

    private String status;

    private Contact contact;
    private String description;
    private byte[] avatar;
    private LocalDate dateOfFirstCall;
   // private LocalDate dateOfRegistration = LocalDate.of(1000, 1 , 1);
    public Customer() {
    }

    public Customer(String name, String status, Contact contact) {
        this.name = name;
        this.status = status;
        this.contact = contact;
    }

    public Customer(String name, String status, Contact contact, String dateOfFirstCall) {
        this.name = name;
        this.status = status;
        this.contact = contact;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.dateOfFirstCall = LocalDate.parse(dateOfFirstCall, formatter);
    }
    public Customer(String name, String status, Contact contact, String dateOfFirstCall, MultipartFile avatar) throws IOException {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", contact=" + contact +
                '}';
    }
}
