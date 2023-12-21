package ru.sfedu.simplepsy.specialist;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.sfedu.simplepsy.customer.Contact;
import ru.sfedu.simplepsy.customer.Customer;

import java.util.List;

@Data
@Document("Specialist")
public class Specialist {

    @Id
    private final String id;
    private final String name;
    private final String surname;
    private final String middleName;
    private String birthDay;
    private String techniques;
    private List<Customer> clients;
    private Contact contacts;
    private int workExperience;
    private double rating;


    public Specialist(String id, String name, String surname, String middleName) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
    }
}
