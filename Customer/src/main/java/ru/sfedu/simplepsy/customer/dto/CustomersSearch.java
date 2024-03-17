package ru.sfedu.simplepsy.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.sfedu.simplepsy.customer.document.Customer;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomersSearch {
    private String specialistId;
    private Set<Customer> customers;
}
