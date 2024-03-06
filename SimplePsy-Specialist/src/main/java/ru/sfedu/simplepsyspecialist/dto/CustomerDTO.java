package ru.sfedu.simplepsyspecialist.dto;

import org.springframework.data.annotation.Id;

public class CustomerDTO {
    @Id
    private String id;

    private String name;
    private Contact contact;

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

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public CustomerDTO(String id, String name, Contact contact) {
        this.id = id;
        this.name = name;
        this.contact = contact;
    }
}
