package ru.sfedu.simplepsy.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@AllArgsConstructor
public class Contact {

    private String phone;

    private String email;

    private String tg;

}
