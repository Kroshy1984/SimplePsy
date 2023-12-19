package ru.sfedu.simplepsy.customer;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Document("customer")
public class Customer {

    @Id
    private String id;

    private final String name;

    private final String status;

    private final Contact contact;

}
